# TMPlayer for Apple: changes

Every version of TMPlayer for Apple, newest first. A version's section is the text of its GitHub
release and of its entry in the AltStore source, so it is written for the people who use the app:
what changed for them, in plain words. The release stops if its section is missing or empty
(`iosApp/release/altstore_source.py notes`).

<!--
A section starts with "## <version>", or "## <version> build <n>" for an iOS-only fix, and may
add the date in brackets, for example:

## 1.28.5 build 2 (2026-11-20)

- What the fix changes, in plain words.

The version and build are the ones the tag names: apple-v1.28.5 releases 1.28.5, build 1, and
apple-v1.28.5-2 its build 2. docs/IOS_RELEASING.md has the rest.
-->

## 1.28.5 build 7 (2026-11-14)

- On an iPad, the player's title and buttons no longer sit under the clock and battery when
  the controls come up.

## 1.28.5 build 6 (2026-11-14)

- A failure while opening a video no longer closes the app.

## 1.28.5 build 5 (2026-11-14)

- Sound and picture stay together. After opening a video where you left it, the sound could run
  seconds ahead and the picture then rushed to catch up.

## 1.28.5 build 4 (2026-11-14)

- A video with sound in more than one language now plays one language at a time and lists them
  all, with all of its subtitles, as on Android. Some MP4 files played two languages over each
  other and listed only one.
- "TMPlayer is struggling on this device" no longer appears when it is other apps that fill the
  device's memory.

## 1.28.5 build 3 (2026-11-14)

- Removing your own TMDB key now clears the posters and details it found, as adding or
  changing one does: series and anime find theirs again from TVmaze and AniList, and films
  show their Telegram picture again.

## 1.28.5 build 2 (2026-11-14)

- Pictures on Home and in a video's details no longer go black, or show another video's picture,
  after the app is opened again.
- Adding your own TMDB key now finds posters and details again by itself, with no need to clear
  them in Settings.

## 1.28.5 (2026-11-14)

The first version for iPhone and iPad, built from TMPlayer 1.28.5.

- Sign in with your phone number or a QR code, then browse your chats and channels and see only
  their videos.
- Playback starts while the video is still downloading, and seeking moves the download to the new
  spot.
- MP4 plays in Apple's own player, which uses the least battery; MKV, AVI, TS and the rest in mpv.
  Both use the hardware decoder, with embedded subtitles (PGS and VobSub too) and several audio
  tracks.
- Episodes recognised from file names, Continue watching, Favourites and Downloads, as in TMPlayer
  for Android.
- One app for iPhone and iPad: the layout follows the window, in either orientation and beside
  another app.
- 22 languages: Czech is new in this version, and the Russian wording is fixed throughout.
- Not on iPhone and iPad: casting (Google Cast, DLNA, AirPlay video), Picture in Picture, and
  downloads while the app is in the background.

Needs iOS or iPadOS 26.4 or later. Unofficial, not affiliated with Telegram.
