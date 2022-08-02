package com.wangkm.jetcaster.ui.player

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.wangkm.jetcaster.Graph
import com.wangkm.jetcaster.data.EpisodeStore
import com.wangkm.jetcaster.data.PodcastStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.Duration

/**
 * @author: created by wangkm
 * @time: 2022/07/08 13:03
 * @desc：
 * @email: 1240413544@qq.com
 */

data class PlayerUiState(
    val title: String = "",
    val subTitle: String = "",
    val duration: Duration? = null,
    val podcastName: String = "",
    val author: String = "",
    val summary: String = "",
    val podcastImageUrl: String = ""
)

/**
 * ViewModel that handles the business logic and screen state of the Player screen
 */
class PlayerViewModel(
    episodeStore: EpisodeStore,
    podcastStore: PodcastStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // episodeUri should always be present in the PlayerViewModel.
    // If that's not the case, fail crashing the app!
    private val episodeUri: String = Uri.decode(savedStateHandle.get<String>("episodeUri")!!)

    var uiState by mutableStateOf(PlayerUiState())
        private set

    init {
        viewModelScope.launch {
            val episode = episodeStore.episodeWithUri(episodeUri).first()
            val podcast = podcastStore.podcastWithUri(episode.podcastUri).first()
            uiState = PlayerUiState(
                title = episode.title,
                duration = episode.duration,
                podcastName = podcast.title,
                summary = episode.summary ?: "",
                podcastImageUrl = podcast.imageUrl ?: ""
            )
        }
    }

    /**
     * Factory for PlayerViewModel that takes EpisodeStore and PodcastStore as a dependency
     */
    companion object {
        fun provideFactory(
            episodeStore: EpisodeStore = Graph.episodeStore,
            podcastStore: PodcastStore = Graph.podcastStore,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return PlayerViewModel(episodeStore, podcastStore, handle) as T
                }
            }
    }
}

