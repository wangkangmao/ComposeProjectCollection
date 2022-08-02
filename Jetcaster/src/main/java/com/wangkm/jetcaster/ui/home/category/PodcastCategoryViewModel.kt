package com.wangkm.jetcaster.ui.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangkm.jetcaster.Graph
import com.wangkm.jetcaster.data.CategoryStore
import com.wangkm.jetcaster.data.EpisodeToPodcast
import com.wangkm.jetcaster.data.PodcastStore
import com.wangkm.jetcaster.data.PodcastWithExtraInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * @author: created by wangkm
 * @time: 2022/07/08 12:26
 * @desc：
 * @email: 1240413544@qq.com
 */

class PodcastCategoryViewModel(
    private val categoryId: Long,
    private val categoryStore: CategoryStore = Graph.categoryStore,
    private val podcastStore: PodcastStore = Graph.podcastStore
) : ViewModel() {
    private val _state = MutableStateFlow(PodcastCategoryViewState())

    val state: StateFlow<PodcastCategoryViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val recentPodcastsFlow = categoryStore.podcastsInCategorySortedByPodcastCount(
                categoryId,
                limit = 10
            )

            val episodesFlow = categoryStore.episodesFromPodcastsInCategory(
                categoryId,
                limit = 20
            )

            // Combine our flows and collect them into the view state StateFlow
            combine(recentPodcastsFlow, episodesFlow) { topPodcasts, episodes ->
                PodcastCategoryViewState(
                    topPodcasts = topPodcasts,
                    episodes = episodes
                )
            }.collect { _state.value = it }
        }
    }

    fun onTogglePodcastFollowed(podcastUri: String) {
        viewModelScope.launch {
            podcastStore.togglePodcastFollowed(podcastUri)
        }
    }
}

data class PodcastCategoryViewState(
    val topPodcasts: List<PodcastWithExtraInfo> = emptyList(),
    val episodes: List<EpisodeToPodcast> = emptyList()
)

