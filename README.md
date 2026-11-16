<p align="center">
  <img src="iosApp/art/logo.svg" width="88" alt="TMPlayer for Apple icon" />
</p>

<h1 align="center">TMPlayer for Apple</h1>

<p align="center"><b>English</b> · <a href="README.it.md">Italiano</a></p>

<p align="center">Your Telegram videos on iPhone and iPad. Start watching before the download finishes.</p>

<p align="center"><sub>TMPlayer for Apple is a free, open source, unofficial Telegram media player that plays the videos in your own Telegram chats on iPhone and iPad, while they are still downloading. It is the iOS and iPadOS version of <a href="https://github.com/dracu-lah/TMPlayer">TMPlayer</a>: for Android TV, Fire TV, Android phones, Windows, macOS and Linux, use the official TMPlayer.</sub></p>

<p align="center">Made by Pietro Giuseppe D'Amico · <a href="https://pietrogdamico.com">pietrogdamico.com</a></p>

<p align="center">
  <img alt="License" src="https://img.shields.io/badge/license-GPL--3.0--or--later-blue" />
  <img alt="Platform" src="https://img.shields.io/badge/platform-iOS%20%7C%20iPadOS%2026.4%2B-000000?logo=apple&logoColor=white" />
</p>

<p align="center">
  <a href="https://buymeacoffee.com/pietrogdamico"><img alt="Buy me a coffee" src="https://img.shields.io/badge/Buy%20me%20a%20coffee-FFDD00?logo=buymeacoffee&logoColor=black" /></a>
  <a href="https://www.paypal.com/paypalme/pietrogdamico28"><img alt="Donate with PayPal" src="https://img.shields.io/badge/PayPal-donate-00457C?logo=paypal&logoColor=white" /></a>
</p>

---

TMPlayer for Apple plays the videos in your own Telegram chats and channels on an iPhone or an
iPad. It starts within seconds while the file is still downloading, and seeking re-aims the
download instead of waiting for it.

Sign in with your Telegram account, pick a chat, and only its playable videos appear. No messages,
no server, no account except yours.

It is [TMPlayer](https://github.com/dracu-lah/TMPlayer), the same app with the same screens and
the same behaviour, brought to iPhone and iPad. TMPlayer's own code runs on iOS as its authors
wrote it; the one part of iOS's own is the video engine. Changes made to TMPlayer reach this
version by merging them in.

## Status

Version 1.28.5, the first for iPhone and iPad, is out: an IPA on [Releases](https://github.com/Killua99x/TMPlayer-for-apple/releases) and an
AltStore source that installs it and offers every update (see [Install](#install)). It is new:
please report what does not work, from the app (About, Report a problem) or in
[Issues](https://github.com/Killua99x/TMPlayer-for-apple/issues).

## Runs on

- **iPhone** with iOS 26.4 or later, and **iPad** with iPadOS 26.4 or later. One app for both.
  26.4 is the oldest version the TDLib build used here supports.
- **Not on a Mac.** The official TMPlayer already has a macOS app (Apple silicon, macOS 26 Tahoe or
  newer): get it from [TMPlayer's releases](https://github.com/dracu-lah/TMPlayer/releases).
- **Apple TV** is not supported yet. I would like to bring TMPlayer for Apple there too, but I do
  not own an Apple TV: if there is interest and enough donations, I will buy one and start on it
  right away (see [Support the project](#support-the-project)).

## What it does

- **Signs in like Telegram does.** Your phone number and the code Telegram sends, or a QR code you
  scan from Telegram on another device. Two-step verification works either way. A short first-run
  setup then asks for the theme, the interface size and a few preferences, and a quick tour shows
  where things are.
- **Shows media, not messages.** Browse the chats and channels you already have, then see only
  their playable videos. Text messages never appear. Videos sent as files are included, so an
  `.mkv` sent as a document is not missed.
- **Starts fast and seeks properly.** Playback begins while the file is still arriving, and
  seeking moves the download to the new spot. The player shows how much of the video has arrived,
  and how quickly. If your connection cannot keep up, *Download the whole video first* in Settings
  fetches it in full before it starts.
- **Plays real-world files.** MKV, MP4, AVI, TS and the rest: MP4 through Apple's own player,
  which uses the least battery, everything else through [mpv](https://mpv.io), both with the
  iPhone's and iPad's hardware decoder. Embedded subtitles, including image-based PGS and VobSub,
  and several audio tracks, switchable while playing.
- **Understands episodes.** File names such as `S02E04`, `2x04` or `Season 2 Episode 4` are
  recognised on the device. Previous and next move between the episodes of the same chat, and the
  next one starts by itself after a countdown you can stop.
- **Remembers where you stopped.** Every video keeps its own position, in Continue watching on
  Home. Star a chat to keep it in Favourites.
- **Keeps what you download.** The video you are watching is cached and cleared when you play the
  next one; videos you download stay in TMPlayer's Downloads until you delete them.
- **Posters and details, if you want them.** The first-run setup offers posters and details from
  TMDB, TVmaze and AniList. Off, your videos are not looked up anywhere.
- **Fits the iPad.** The layout follows the window, full screen or beside another app, in either
  orientation, and a hardware keyboard works in the player.
- **Signing out clears everything** it kept: the Telegram session, your history, favourites,
  settings and downloaded videos.

TMPlayer's [feature list](https://github.com/dracu-lah/TMPlayer/blob/main/docs/FEATURES.md) describes every function at length. It is written for
Android, TVs and computers, so not all of it applies here: the lists on this page are the iPhone's
and the iPad's.

## What it does not do on iPhone and iPad

- **No casting of any kind:** no Google Cast, no DLNA, no AirPlay video. AirPlay screen mirroring
  of the whole screen, from Control Center, still works.
- **No Picture in Picture.** iOS offers it only to players that hand their picture to Apple's own
  video layers: mpv does not, and Apple's player plays only some files.
- **No downloads in the background.** iOS does not let the app keep its Telegram connection for
  long once it is out of sight, so downloads go on while the app is open.
- **No updater inside the app.** Updates come through AltStore or SideStore (see below).

## Install

### With AltStore or SideStore (recommended)

[AltStore](https://altstore.io) and [SideStore](https://sidestore.io) install apps from outside the
App Store, signed with your own Apple ID, and keep them up to date from a *source*: a web address
that lists an app's versions.

1. Install AltStore or SideStore on your iPhone or iPad, following its own guide.
2. Copy the address of the TMPlayer for Apple source:

   ```
   https://raw.githubusercontent.com/Killua99x/TMPlayer-for-apple/main/altstore/source.json
   ```

3. In AltStore or SideStore, open **Sources**, tap **+**, paste the address and confirm.
4. Find **TMPlayer for Apple** in the source and tap **Get** (or **Free**). Every new version then
   shows up under updates.

With a free Apple ID, Apple lets an app signed this way run for 7 days: AltStore and SideStore
refresh it before then (AltStore when the computer running AltServer is on the same Wi-Fi,
SideStore on the device itself). A free Apple ID also has at most 3 such apps at once. A paid Apple
developer account lifts both limits.

### With the IPA

Every [release](https://github.com/Killua99x/TMPlayer-for-apple/releases) also carries the `.ipa` file, with its SHA-256 in `SHA256SUMS.txt`. Any
sideloading tool can install it, Sideloadly for example; updates are then up to you, by installing
the next release's IPA.

## Build it yourself

You need a Mac with **Xcode 26.4 or newer** (the iOS 26.4 SDK), **JDK 21** (with Homebrew,
`brew install openjdk@21`), and your own free Telegram credentials from
[my.telegram.org](https://my.telegram.org) in `local.properties` at the repository root:

```properties
sdk.dir=/path/to/Android/Sdk
TG_API_ID=1234567
TG_API_HASH=your_api_hash
```

`sdk.dir` is there because the same Gradle build holds TMPlayer's Android app, which Gradle sets up
even when it builds for iOS: it needs the Android SDK's path (Android Studio's, or the command line
tools'), not an Android device. Without the credentials the app builds and says so instead of
offering to sign in. Then open
`iosApp/TMPlayer.xcodeproj` and run the `TMPlayer` scheme. On an iPhone or iPad, set your own Apple
development team in the target's **Signing & Capabilities** first; the simulator needs an Apple
silicon Mac. Xcode's first build phase runs Gradle, which finds JDK 21 by itself.

From the command line:

```bash
xcodebuild -project iosApp/TMPlayer.xcodeproj -scheme TMPlayer -destination 'generic/platform=iOS Simulator' build
```

The tests run on the simulator:

```bash
./gradlew :core:iosSimulatorArm64Test :ui:iosSimulatorArm64Test :ios:iosSimulatorArm64Test
```

A release IPA, as published, comes from `iosApp/release/build-ipa.sh <version> <build> <folder>`.
It fits an 8 GB Mac: the Kotlin half's optimised link runs alone, with about 6 GB, for some 20
minutes.

## How it is made

- **TMPlayer's own code, unchanged.** Its Kotlin code (the screens, the player's controls, the
  downloads, the settings) is compiled for iOS with Kotlin/Native and
  [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/). Where it calls
  Android's or Java's own APIs, this repository supplies versions of them made for iOS, so the
  same files build for both.
- **Telegram through TDLib,** Telegram's own client library, by way of
  [tdl-coroutines](https://github.com/g000sha256/tdl-coroutines).
- **Video through Apple's player (AVPlayer)** for MP4 and QuickTime files, and **libmpv** from
  [MPVKit](https://github.com/mpvkit/MPVKit), in its LGPL build, with FFmpeg, VideoToolbox's
  hardware decoding and [MoltenVK](https://github.com/KhronosGroup/MoltenVK) for the picture, for
  the rest. They are the one part of iOS's own, behind the same commands the Android player gives
  its engine, and Android's Decoder setting chooses between them.
- **As light as it can be.** Measured on the devices: no work while nothing changes on screen, no
  threads left waiting for nothing, pictures kept within a fixed memory budget.

## Support the project

TMPlayer for Apple has no ads of its own (Telegram's sponsored messages appear in public channels,
as Telegram requires) and no paid tier. Donations pay for the Apple devices and tools it is built
and tested with.

**An Apple TV version** is what I would like to make next, but I do not own an Apple TV to build
and test it on. If there is interest and the donations allow it, I will buy one and start on it
right away. If you would use it, say so in this repository's [Issues](../../issues).

- [Buy me a coffee](https://buymeacoffee.com/pietrogdamico)
- [PayPal](https://www.paypal.com/paypalme/pietrogdamico28)

TMPlayer itself is made by dracu-lah: you can support it too, through
[GitHub Sponsors](https://github.com/sponsors/dracu-lah). Either way, a star helps the next person
trust it.

**Bugs and requests** for iPhone and iPad go in this repository's [Issues](../../issues). For
Android, TV and the desktop, use [TMPlayer's own](https://github.com/dracu-lah/TMPlayer/issues).

## Questions

**Why not the App Store?** TMPlayer is licensed under the GPL 3.0, and the App Store's terms
conflict with it: any of its authors could have the app removed. It would take a written
permission from TMPlayer's authors. AltStore and SideStore need none.

**Is it safe to sign in?** The app talks to Telegram directly through TDLib, as Telegram's own apps
do. There is no server of this project's in between, no analytics and no crash reporting. Your
session stays on the device until you sign out.

**Does it work without an Apple developer account?** Yes. A free Apple ID is enough for AltStore,
SideStore and building it yourself; the 7-day refresh is the difference.

## Legal

Unofficial client. Not affiliated with, endorsed by, or connected to Telegram FZ-LLC, nor with
Apple Inc. It uses the official Telegram API through TDLib with your own Telegram account, as
[permitted for third-party clients](https://core.telegram.org/api/obtaining_api_id). iPhone, iPad
and Apple TV are trademarks of Apple Inc.

TMPlayer for Apple does not provide media, recommend channels, or bypass access controls.
Telegram's own restrictions stay on. Use it only with content you own or are authorized to access,
as TMPlayer's [Lawful use](https://tmplayer.org/legal/) page says.

TMPlayer for Apple is an independent port: it is not published or supported by TMPlayer's
developer, so its problems and questions belong in this repository's
[issues](https://github.com/Killua99x/TMPlayer-for-apple/issues), not TMPlayer's.

What the app keeps on your device and which services it talks to is in [Privacy](PRIVACY.md).

## License

[GNU GPL-3.0-or-later](LICENSE) © 2026 dracu-lah, with an
[additional permission for OpenSSL](LICENSE-OPENSSL-EXCEPTION.md) (TDLib links OpenSSL). The iOS and
iPadOS port, by Pietro Giuseppe D'Amico ([pietrogdamico.com](https://pietrogdamico.com)), is published
under the same licence.

TMPlayer for Apple is a modified version of [TMPlayer](https://github.com/dracu-lah/TMPlayer) by
dracu-lah. What was changed, from November 2026: the iOS and iPadOS port (the `ios` module,
`iosApp/`, the iOS sources and stand-ins in `core` and `ui`, and the build steps that compile
TMPlayer's screens for iOS), and its own name, icon and links. The rest is TMPlayer's code as its
developer wrote it, following its releases. The name TMPlayer and its player mark belong to the
original project: this version is published as TMPlayer for Apple, with its own icon, as
TMPlayer's developer agreed (16 November 2026).

The iOS build adds TDLib (Boost Software License 1.0) through tdl-coroutines (Apache License 2.0),
libmpv and FFmpeg in their LGPL builds through MPVKit, MoltenVK (Apache License 2.0), and Kotlin
and Compose Multiplatform (Apache License 2.0). The libraries TMPlayer itself bundles are in
[THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).

The TMPlayer for Apple icon, a chat bubble with a play triangle ([iosApp/art](iosApp/art)), was
made for this project and is published under the same licence.
