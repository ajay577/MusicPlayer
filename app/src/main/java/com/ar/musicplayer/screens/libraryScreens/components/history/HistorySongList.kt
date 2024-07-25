package com.ar.musicplayer.screens.libraryScreens.components.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ar.musicplayer.di.roomdatabase.favoritedb.FavoriteViewModel
import com.ar.musicplayer.di.roomdatabase.lastsession.LastSessionEvent
import com.ar.musicplayer.di.roomdatabase.lastsession.LastSessionViewModel
import com.ar.musicplayer.models.SongResponse
import com.ar.musicplayer.viewmodel.PlayerViewModel

@Composable
fun HistorySongList(
    songResponseList: List<Pair<Int?, SongResponse>>,
    playerViewModel: PlayerViewModel,
    favViewModel: FavoriteViewModel,
    lastSessionViewModel: LastSessionViewModel
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(songResponseList.toList()) {(id,songResponse) ->
                HistorySongItem(
                    songResponse = songResponse,
                    onTrackSelect = {
                        if(id != null){
                            lastSessionViewModel.onEvent(LastSessionEvent.DeleteHistoryById(id))
                        }
                    },
                    onClick = {
                        playerViewModel.starter.value = false
                        playerViewModel.updateCurrentSong(
                            songResponse
                        )
                        playerViewModel.isPlayingHistory.value = false
                    }
                )
            }
            item{
                Spacer(modifier = Modifier.height(125.dp))
            }
        }
    }
}

