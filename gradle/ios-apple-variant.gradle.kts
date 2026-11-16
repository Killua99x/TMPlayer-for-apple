// TMPlayer for Apple (docs/IOS_PORT_PLAN.md, M6): the few places where the iOS app names its own
// things where upstream's code names upstream's. They cover where its source is, where its users
// report a problem, where its privacy policy is, where a donation goes and where its changelog is
// (the maintainer, 2026-11-13). Upstream's files stay as they are. :core, :ui and :ios compile, for
// iOS only, copies of the files below with these exact replacements. A replacement whose text is
// no longer in upstream's file stops the build, so a change upstream is seen and never skipped.
//
// Applied with apply(from = ...). The build reads back `extra["appleVariant"]`, a map from module
// to the source paths it changes, and `extra["forApple"]` as `(String, String, String) -> String`
// (module, path, text).

/** The public repository the Apple variant is published from. */
val appleSource = "https://github.com/Killua99x/TMPlayer-for-apple"

/** The maintainer's two ways to give (README.md, "Support the project"). */
val appleCoffee = "https://buymeacoffee.com/pietrogdamico"
val applePaypal = "https://www.paypal.com/paypalme/pietrogdamico28"

/**
 * TMPlayer for Apple's mark in a 1024 square, as in iosApp/art/icon.svg: a chat bubble with a play
 * triangle cut out of it (filled even-odd), white on the deep blue to azure tile.
 */
val appleMark = "M352 224H672A160 160 0 0 1 832 384V544A160 160 0 0 1 672 704H430L262 818Q236 836 246 806" +
    "L290 691A160 160 0 0 1 192 544V384A160 160 0 0 1 352 224Z " +
    "M447 382Q447 352 473 367L616 449Q642 464 616 479L473 561Q447 576 447 546Z"

/** One replacement: every [old] in the file becomes [new], and [old] must be there at least once. */
data class Replacement(val old: String, val new: String)

infix fun String.becomes(new: String) = Replacement(this, new)

val appleVariant: Map<String, Map<String, List<Replacement>>> = mapOf(
    // :core, paths under src/commonMain/kotlin.
    "core" to mapOf(
        // "Report a problem" opens an issue in the Apple repository, with its own template whose
        // platform dropdown has the option below. There is no email address (the maintainer).
        "com/tmplayer/data/Feedback.kt" to listOf(
            "const val ISSUES = \"https://github.com/dracu-lah/TMPlayer/issues/new\"" becomes
                "const val ISSUES = \"$appleSource/issues/new\"",
            "const val TEMPLATE = \"bug_report.yml\"" becomes "const val TEMPLATE = \"apple_bug_report.yml\"",
            "Phone(\"Android phone or tablet\")," becomes "Phone(\"iPhone or iPad\"),",
        ),
        "com/tmplayer/data/WhatsNew.kt" to listOf(
            "const val CHANGELOG = \"https://tmplayer.org/changelog/\"" becomes
                "const val CHANGELOG = \"$appleSource/blob/main/iosApp/CHANGELOG.md\"",
        ),
    ),
    // :ui, paths under src/shared/kotlin.
    "ui" to mapOf(
        // TMPlayer for Apple's own name and mark (iosApp/art/icon.svg), where every screen draws
        // the app's lockup: TMPlayer's name and player mark stay with TMPlayer (its developer,
        // 2026-11-16: "different image and name ... tmplayer for apple is fine").
        "com/tmplayer/ui/components/AppLogo.kt" to listOf(
            "const val BRAND_NAME = \"TMPlayer\"" becomes "const val BRAND_NAME = \"TMPlayer for Apple\"",
            "            layer(0xFF1E1E28, TILE)\n" +
                "            layer(0xFF0E0E12, SCREEN)\n" +
                "            layer(0xFF3A3A46, TRACK)\n" +
                "            layer(0xFF2AABEE, PROGRESS)\n" +
                "            layer(0xFFEDEDF2, PLAYHEAD_AND_TRIANGLE)\n" becomes
                "            addPath(\n" +
                "                PathParser().parsePathString(\"M0 0H1024V1024H0Z\").toNodes(),\n" +
                "                fill = androidx.compose.ui.graphics.Brush.linearGradient(\n" +
                "                    listOf(Color(0xFF2E5BFF), Color(0xFF1FC8FF)),\n" +
                "                    start = androidx.compose.ui.geometry.Offset(0f, 0f),\n" +
                "                    end = androidx.compose.ui.geometry.Offset(1024f, 1024f),\n" +
                "                ),\n" +
                "            )\n" +
                "            addPath(\n" +
                "                PathParser().parsePathString(\"$appleMark\").toNodes(),\n" +
                "                pathFillType = androidx.compose.ui.graphics.PathFillType.EvenOdd,\n" +
                "                fill = SolidColor(Color.White),\n" +
                "            )\n",
        ),
        "com/tmplayer/ui/about/About.kt" to listOf(
            "const val SOURCE = \"https://github.com/dracu-lah/TMPlayer\"" becomes
                "const val SOURCE = \"$appleSource\"\n" +
                "\n" +
                "    /** The project this app is based on, credited under Licence. */\n" +
                "    const val UPSTREAM = \"https://github.com/dracu-lah/TMPlayer\"",
            "const val PRIVACY = \"https://tmplayer.org/privacy/\"" becomes
                "const val PRIVACY = \"\$SOURCE/blob/main/PRIVACY.md\"",
            // The second way to give is PayPal on iOS: the screens name it where they named
            // GitHub Sponsors (the :ios replacements below).
            "const val SPONSORS = \"https://github.com/sponsors/dracu-lah\"" becomes
                "const val SPONSORS = \"$applePaypal\"",
            "const val COFFEE = \"https://buymeacoffee.com/nevil.dev\"" becomes
                "const val COFFEE = \"$appleCoffee\"",
            // The README's section lists both ways. A GitHub page keeps no visit counts, so the
            // place the viewer came from is not added to the address.
            "const val SUPPORT_PAGE = \"https://tmplayer.org/support/\"" becomes
                "const val SUPPORT_PAGE = \"\$SOURCE#support-the-project\"",
            "fun supportUrl(from: String): String = \"\$SUPPORT_PAGE?utm_source=\$from&utm_medium=app\"" becomes
                "fun supportUrl(@Suppress(\"UNUSED_PARAMETER\") from: String): String = SUPPORT_PAGE",
            "L.aboutSupportPageDetail," becomes "L.aboutSupportPageDetailIos,",
            // The Apple repository's releases are tagged apple-v<version> (apple-release.yml).
            "\"\$SOURCE/releases/tag/v\$version\"" becomes "\"\$SOURCE/releases/tag/apple-v\$version\"",
            "L.aboutCorrespondingSourceDetail," becomes "L.aboutCorrespondingSourceDetailIos,",
            "Link(L.aboutSourceCode, L.aboutSourceCodeDetail, SOURCE)," becomes
                "Link(L.aboutSourceCode, L.aboutSourceCodeDetail, SOURCE),\n" +
                "                Link(\"TMPlayer\", L.aboutBasedOnDetail, UPSTREAM),",
            // Contact: the repository's issues only, no email and not upstream's Telegram.
            "                Link(L.aboutEmail, EMAIL, \"mailto:\$EMAIL\"),\n" becomes "",
            "                Link(L.aboutTelegramChannel, L.aboutTelegramChannelDetail, \"https://t.me/tmplayerapp\"),\n" becomes "",
            "                Link(L.aboutTelegramGroup, L.aboutTelegramGroupDetail, \"https://t.me/tmplayer_chat\"),\n" becomes "",
            "Link(\"GitHub Discussions\", L.aboutDiscussionsDetail, \"\$SOURCE/discussions\")," becomes
                "Link(\"GitHub Issues\", L.aboutDiscussionsDetail, \"\$SOURCE/issues\"),",
        ),
        // The iOS app carries no TMDB key of its own, so with none added TMDB was never asked:
        // "TMDB did not accept the key" read as a fault (iPad, 2026-11-14). Settings' own words
        // for a missing key say what to do; a key of one's own that TMDB refuses keeps upstream's.
        "com/tmplayer/ui/browse/PosterPicker.kt" to listOf(
            "PosterChoices.NoKey -> PickerLoad.Message(s.posterNoKey, canRetry = false)" becomes
                "PosterChoices.NoKey -> PickerLoad.Message(\n" +
                "                if (online.store.now.ownKey.isBlank()) s.metadataKeyNone else s.posterNoKey,\n" +
                "                canRetry = false,\n" +
                "            )",
        ),
    ),
    // :ios, Android's screens, paths under app/src/main/java.
    "ios" to mapOf(
        // Android's launcher drawing (ic_logo) is TMPlayer's mark: the iOS app draws its own.
        "com/tmplayer/ui/components/AppMark.kt" to listOf(
            "painter = painterResource(R.drawable.ic_logo)," becomes
                "painter = androidx.compose.ui.graphics.vector.rememberVectorPainter(AppLogo.Mark),",
        ),
        "com/tmplayer/ui/settings/AboutScreen.kt" to listOf(
            "s.supportSponsorGithub" becomes "\"PayPal\"",
        ),
        "com/tmplayer/ui/settings/Support.kt" to listOf(
            "s.supportSponsorGithub" becomes "\"PayPal\"",
            // No UPI: the maintainer takes Buy Me a Coffee and PayPal.
            "                UpiPay(modifier = Modifier.fillMaxWidth())\n" becomes "",
            "                    UpiCode()\n" becomes "",
        ),
        "com/tmplayer/ui/settings/LanguageAndFeedback.kt" to listOf(
            "                TmSecondaryButton(\n" +
                "                    onClick = { if (sendMail(context, Feedback.mailto(facts))) onClose() },\n" +
                "                    modifier = Modifier.fillMaxWidth(),\n" +
                "                ) { Label(s.feedbackEmail) }\n" +
                "                Text(s.feedbackEmailDetail(Feedback.EMAIL), style = MaterialTheme.typography.bodySmall, color = Tone.muted)\n"
                becomes "",
            // A bug report's and a saved log's facts: the device as iOS names it ("iPad
            // (iPad13,18), iPadOS 27.0.1"), not "Apple iPad, Android 27.0.1" (an iPad's log,
            // 2026-11-15), and the build, since builds of one version differ on iOS.
            "    val model = listOf(Build.MANUFACTURER, Build.MODEL).filter { it.isNotBlank() }.distinct().joinToString(\" \")\n" becomes
                "    val model = com.tmplayer.data.appleDevice()\n",
            "\"\$model, Android \${Build.VERSION.RELEASE}, " becomes "\"\$model, ",
            "version = BuildConfig.VERSION_NAME," becomes "version = com.tmplayer.data.Updates.installedVersion,",
        ),
        // The iOS app checks for no update itself: AltStore or SideStore does, from the source.
        "com/tmplayer/ui/settings/SettingsScreen.kt" to listOf(
            "s.settingsPrivacyNote," becomes "s.settingsPrivacyNoteIos,",
            // The changelog is iosApp/CHANGELOG.md on GitHub (WhatsNew.CHANGELOG above).
            "s.settingsChangelogDetail," becomes "s.settingsChangelogDetailIos,",
        ),
    ),
)

fun forApple(module: String, path: String, text: String): String {
    val replacements = appleVariant[module]?.get(path) ?: return text
    return replacements.fold(text) { current, (old, new) ->
        if (!current.contains(old)) {
            throw GradleException(
                "$module/$path no longer contains the text the Apple variant replaces " +
                    "(gradle/ios-apple-variant.gradle.kts): ${old.trim().take(120)}",
            )
        }
        current.replace(old, new)
    }
}

extra["appleVariant"] = appleVariant.mapValues { (_, files) -> files.keys.sorted() }
extra["forApple"] = { module: String, path: String, text: String -> forApple(module, path, text) }
