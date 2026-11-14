package com.tmplayer.ios

import com.tmplayer.online.OnlineMetadata
import com.tmplayer.platform.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A TMDB key of one's own, added or changed in Settings, clears the posters and details already
 * found, as "Clear posters and details" does: what was matched without TMDB (TVmaze's and AniList's
 * answers, and the titles nothing was found for) is kept for months, so without this a new key
 * changed nothing until the viewer found that button. Asked for by the maintainer (2026-11-14);
 * Android's TMPlayer still leaves it to the button. A key taken away clears nothing: what TMDB
 * answered stays right.
 */
internal object OwnKeyWatch {
    private var started = false

    fun start() {
        if (started) return
        val online = OnlineMetadata.current ?: return
        started = true
        com.tmplayer.App.backgroundScope.launch {
            online.store.settings
                .map { it.ownKey }
                .distinctUntilChanged()
                .drop(1)
                .filter { it.isNotBlank() }
                .collect {
                    runCatching { withContext(Dispatchers.Default) { online.purge() } }
                        .onSuccess { Logger.i(TAG, "a new TMDB key: posters and details cleared, to be found again") }
                        .onFailure { Logger.w(TAG, "the posters and details could not be cleared", it) }
                }
        }
    }

    private const val TAG = "OwnKeyWatch"
}
