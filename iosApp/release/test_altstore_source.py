"""Tests for altstore_source.py. Run from the repository root:

    python3 -I -m unittest discover -s iosApp/release
"""

import contextlib
import io
import json
import os
import plistlib
import shutil
import tempfile
import unittest
import zipfile

import altstore_source

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
BUNDLE = "org.example.player"


def make_ipa(folder, version="1.0.0", build="1", bundle=BUNDLE, extra=None, name="Player.ipa"):
    info = {
        "CFBundleIdentifier": bundle,
        "CFBundleShortVersionString": version,
        "CFBundleVersion": build,
        "MinimumOSVersion": "26.4",
        "NSPhotoLibraryAddUsageDescription": "Saves the videos you choose to Photos.",
        "UIBackgroundModes": ["audio"],
    }
    info.update(extra or {})
    path = os.path.join(folder, name)
    with zipfile.ZipFile(path, "w") as ipa:
        ipa.writestr("Payload/Player.app/Info.plist", plistlib.dumps(info, fmt=plistlib.FMT_BINARY))
        ipa.writestr("Payload/Player.app/Player", b"\0" * 64)
    return path


def make_source(folder):
    source = {
        "name": "Player",
        "apps": [{
            "name": "Player",
            "bundleIdentifier": BUNDLE,
            "localizedDescription": "Plays videos.",
            "category": "entertainment",
            "versions": [],
            "appPermissions": {"entitlements": [], "privacy": {}},
        }],
        "news": [],
    }
    path = os.path.join(folder, "source.json")
    with open(path, "w", encoding="utf-8") as file:
        json.dump(source, file)
    return path


def run(*argv):
    out = io.StringIO()
    with contextlib.redirect_stdout(out), contextlib.redirect_stderr(io.StringIO()):
        altstore_source.main(list(argv))
    return out.getvalue()


class AddTest(unittest.TestCase):
    def setUp(self):
        self.folder = tempfile.mkdtemp()
        self.addCleanup(shutil.rmtree, self.folder)
        self.source = make_source(self.folder)

    def add(self, ipa, *more):
        run("add", "--source", self.source, "--ipa", ipa, "--download-url", "https://example.org/" + os.path.basename(ipa),
            "--date", "2026-12-01T10:00:00Z", *more)
        with open(self.source, encoding="utf-8") as file:
            return json.load(file)

    def test_a_version_goes_on_top_with_the_ipas_permissions(self):
        self.add(make_ipa(self.folder, "1.0.0", "1"))
        notes = os.path.join(self.folder, "notes.md")
        with open(notes, "w", encoding="utf-8") as file:
            file.write("- Faster starts.\n")
        source = self.add(make_ipa(self.folder, "1.1.0", "2", name="Next.ipa"), "--notes-file", notes,
                          "--repo", "owner/TMPlayer-for-apple", "--developer", "owner")
        app = source["apps"][0]
        self.assertEqual(["1.1.0", "1.0.0"], [v["version"] for v in app["versions"]], "newest first")
        latest = app["versions"][0]
        self.assertEqual("2", latest["buildVersion"])
        self.assertEqual("26.4", latest["minOSVersion"])
        self.assertEqual("- Faster starts.", latest["localizedDescription"])
        self.assertEqual(os.path.getsize(os.path.join(self.folder, "Next.ipa")), latest["size"])
        self.assertEqual({"entitlements": [], "privacy": {"NSPhotoLibraryAddUsageDescription": "Saves the videos you choose to Photos."}},
                         app["appPermissions"], "usage descriptions only, as the IPA has them")
        self.assertEqual(("1.1.0", latest["downloadURL"], latest["size"]), (app["version"], app["downloadURL"], app["size"]),
                         "the latest version also at the app's level, for older AltStore and SideStore")
        self.assertEqual("https://raw.githubusercontent.com/owner/TMPlayer-for-apple/main/altstore/source.json", source["sourceURL"])
        self.assertTrue(app["iconURL"].endswith("/AppIcon.png"))
        self.assertEqual("owner", app["developerName"])
        self.assertEqual(f"{BUNDLE}.source", source["identifier"])

    def test_the_same_version_made_again_replaces_its_entry(self):
        self.add(make_ipa(self.folder, "1.0.0", "1"))
        source = self.add(make_ipa(self.folder, "1.0.0", "7"))
        versions = source["apps"][0]["versions"]
        self.assertEqual(1, len(versions))
        self.assertEqual("7", versions[0]["buildVersion"])

    def test_an_ipa_of_another_app_is_refused(self):
        with self.assertRaises(SystemExit) as refused:
            self.add(make_ipa(self.folder, bundle="org.example.other"))
        self.assertIn("org.example.other", str(refused.exception))

    def test_a_tag_and_an_ipa_that_disagree_are_refused(self):
        with self.assertRaises(SystemExit) as refused:
            self.add(make_ipa(self.folder, "1.0.0"), "--version", "1.0.1")
        self.assertIn("1.0.1", str(refused.exception))

    def test_a_tag_and_an_ipa_of_another_build_are_refused(self):
        with self.assertRaises(SystemExit) as refused:
            self.add(make_ipa(self.folder, "1.0.0", "1"), "--version", "1.0.0", "--build", "2")
        self.assertIn("build 2", str(refused.exception))

    def test_a_file_that_is_not_an_ipa_is_refused(self):
        bad = os.path.join(self.folder, "bad.ipa")
        with open(bad, "wb") as file:
            file.write(b"not a zip")
        with self.assertRaises(SystemExit):
            self.add(bad)

    def test_the_result_passes_the_published_check(self):
        self.add(make_ipa(self.folder), "--repo", "owner/name", "--developer", "owner")
        self.assertIn("in order", run("check", "--source", self.source, "--published"))


class NotesTest(unittest.TestCase):
    def setUp(self):
        self.folder = tempfile.mkdtemp()
        self.addCleanup(shutil.rmtree, self.folder)
        self.changelog = os.path.join(self.folder, "CHANGELOG.md")
        with open(self.changelog, "w", encoding="utf-8") as file:
            file.write("# Changes\n\n<!--\n## 9.9.9\n- not this\n-->\n\n## 1.1.0 (2026-12-02)\n\n- Two.\n- Three.\n\n"
                       "## 1.0.0 build 2 (2026-12-01)\n\n- Fix.\n\n## 1.0.0\n\n- One.\n\n## 1.0.0.5\n\n- Not one.\n")

    def notes(self, version, build="1"):
        return run("notes", "--changelog", self.changelog, "--version", version, "--build", build).strip()

    def test_a_section_is_its_lines_up_to_the_next(self):
        self.assertEqual("- Two.\n- Three.", self.notes("1.1.0"))
        self.assertEqual("- One.", self.notes("1.0.0"))

    def test_a_later_build_has_its_own_section(self):
        self.assertEqual("- Fix.", self.notes("1.0.0", "2"))
        for build in ("3", "0", "two"):
            with self.assertRaises(SystemExit):
                self.notes("1.0.0", build)

    def test_a_missing_or_commented_section_stops_the_release(self):
        for version in ("2.0.0", "9.9.9"):
            with self.assertRaises(SystemExit):
                self.notes(version)


class RepositoryTest(unittest.TestCase):
    def test_the_source_matches_the_xcode_project(self):
        output = run("check", "--source", os.path.join(ROOT, "altstore", "source.json"),
                     "--project", os.path.join(ROOT, "iosApp", "project.yml"))
        self.assertIn("in order", output)

    def test_a_source_without_versions_is_not_ready_to_publish(self):
        with self.assertRaises(SystemExit):
            run("check", "--source", os.path.join(ROOT, "altstore", "source.json"), "--published")


if __name__ == "__main__":
    unittest.main()
