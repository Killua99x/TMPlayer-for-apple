<p align="center"><b>English</b> · <a href="PRIVACY.it.md">Italiano</a></p>

# Privacy

TMPlayer for Apple, for iPhone and iPad. Effective 13 November 2026.

TMPlayer for Apple is an unofficial Telegram video player. It talks to Telegram directly from your
iPhone or iPad, with your own Telegram account. There is no TMPlayer account, and no server run by
the developer that your data goes to. The developer never receives your Telegram sign in, your
chats, your file names, what you watch or your videos.

It is free, open source software published by its developer (Killua99x on GitHub), and based on
[TMPlayer](https://github.com/dracu-lah/TMPlayer) for Android, TV and computers. Questions about
this page go to the [issues](https://github.com/Killua99x/TMPlayer-for-apple/issues) of this
repository. Issues are public: never put your phone number, a sign in code or other personal data
in one.

## What stays on your device

Telegram's TDLib keeps your signed in session, cached chat information, thumbnails and the parts of
videos you have watched. TMPlayer keeps your settings, favourites, Continue watching places and the
videos you download. All of it is inside the app's own storage on your iPhone or iPad, where other
apps cannot read it, and deleting the app deletes all of it. A video you save somewhere else, in the
Files app or a folder you pick, is a copy that stays there.

While a video plays, its title shows in iOS's Now Playing controls, so the lock screen, Control
Center and a paired device can show it.

## Resume on your other devices

Off until you turn it on in Settings. With it on, TMPlayer keeps your Continue watching places in
one message in your own Telegram Saved Messages, so another device signed in to the same account
can pick up where you stopped. Nothing goes to the developer: the message is in your account like
any other. It holds, for up to 40 recent videos, the chat and message the video came from, where
you stopped, its length, when you last watched it, its title, its chat's name, its file name and
its size. Turning the setting off stops it at once. The message itself stays in Saved Messages
until you delete it there.

## Proxies

If Telegram is blocked where you are, you can add a proxy in Settings (MTProto, SOCKS5 or HTTP). It
is stored in TDLib's database on your device and used for the connection to Telegram. TMPlayer
runs no proxy and ships no list of them. What you type goes only to the proxy you chose, which sees
your IP address.

## Who TMPlayer talks to

Each of these sees your IP address, as any connection does. None is sent your Telegram account.

- **Telegram**, for sign in, your chats and channels, and the videos and their thumbnails, under
  [Telegram's privacy policy](https://telegram.org/privacy). Where Telegram shows a sponsored
  message, TMPlayer tells Telegram when it was seen and when it was tapped, as every Telegram app
  must.
- **TMDB, TVmaze and AniList**, for posters and overviews. On by default; switch it off in
  Settings, Posters and details. A lookup sends the video's cleaned up title, and the year, season
  and episode read from its name, never the file. Answers are kept on your device for at most six
  months, and Settings can clear them.
- **OMDb and Wikidata**, for a title's IMDb, Rotten Tomatoes and Metacritic scores, when you open its
  page while those lookups are on. A request sends only the IMDb id of the title the lookup found.
- **TheIntroDB, IntroDB.app and AniSkip**, for where an episode's intro, recap and credits are, for
  the player's skip button, while the lookups and Skip intro buttons are both on (they are by
  default). A request sends the id of the title the lookup found, and the season and episode.
- **OpenSubtitles.com**, only when you choose Search online in the subtitle picker. A search sends
  the video's file name or its title, season and episode, its OpenSubtitles hash (a number made
  from its size and its first and last 64 KB, never the file) and the languages you want. Signing
  in under Settings, Online subtitles, sends your OpenSubtitles username and password to
  OpenSubtitles.
- **SubDL**, the same kind of search, only with your own SubDL key, if you entered one.
- **MSone** Malayalam subtitles, only when you choose MSone in the subtitle picker, through the
  gateway at subs.tmplayer.org that the developer of TMPlayer runs on Cloudflare. A search sends
  the title's IMDb or TMDB id, or its cleaned up title and year, and the season and episode.
- **tmplayer.org and api.github.com**, only when Telegram has not answered for a while: TMPlayer
  opens a connection to each to tell whether the internet works at all, and sends nothing over it.

TMPlayer does not check for updates itself. AltStore or SideStore reads this repository's source
file on GitHub to offer each new version, under their own policies and GitHub's.

A link you open from the app (Buy Me a Coffee, PayPal, GitHub, a page like this one) opens in your
browser, and that site's own policy applies. The app sends nothing with it.

## What TMPlayer does not do

No analytics, no advertising SDK, no tracking across apps, and no crash reports. Report a problem
in Settings opens a new GitHub issue in your browser with the app version, the device model, the
iOS version and the app language filled in. Nothing is sent unless you submit it, and you can
change or remove any of it first.

## Deleting your data

- Sign out of Telegram, in Settings, logs out of Telegram on this device and clears TDLib's database, the watch
  cache, your settings, favourites, Continue watching and the watched list. Your downloads stay
  unless you tick Also delete my downloads.
- Deleting the app deletes everything it stored on the device.
- Your Telegram account is Telegram's, not TMPlayer's. To delete it, use
  [telegram.org/deactivate](https://telegram.org/deactivate).

## Your rights

Where a law such as the GDPR applies to you, you can ask what personal data the developer holds,
and have it corrected or deleted, and you can complain to the data protection authority where you
live. The developer holds no personal data about the people who use the app: no account, no
reports, no logs.

## Changes

A change to what this page says updates the date at the top and is listed here.

- 13 November 2026: first version.
