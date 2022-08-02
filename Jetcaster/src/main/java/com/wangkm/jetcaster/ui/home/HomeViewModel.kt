package com.wangkm.jetcaster.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangkm.jetcaster.Graph
import com.wangkm.jetcaster.data.PodcastStore
import com.wangkm.jetcaster.data.PodcastWithExtraInfo
import com.wangkm.jetcaster.data.PodcastsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * @author: created by wangkm
 * @time: 2022/07/06 13:23
 * @desc：
 * @email: 1240413544@qq.com
 */

class HomeViewModel(
    private val podcastsRepository: PodcastsRepository = Graph.podcastRepository,
    private val podcastStore: PodcastStore = Graph.podcastStore
) : ViewModel() {
    // Holds our currently selected home category
    private val selectedCategory = MutableStateFlow(HomeCategory.Discover)
    // Holds the currently available home categories
    private val categories = MutableStateFlow(HomeCategory.values().asList())

    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(HomeViewState())

    private val refreshing = MutableStateFlow(false)

    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.
            combine(
                categories,
                selectedCategory,
                podcastStore.followedPodcastsSortedByLastEpisode(limit = 20),
                refreshing
            ) { categories, selectedCategory, podcasts, refreshing ->
                HomeViewState(
                    homeCategories = categories,
                    selectedHomeCategory = selectedCategory,
                    featuredPodcasts = podcasts,
                    refreshing = refreshing,
                    errorMessage = null /* TODO */
                )
            }.catch { throwable ->
                // TODO: emit a UI error here. For now we'll just rethrow
                throw throwable
            }.collect {
                _state.value = it
            }
        }

        refresh(force = false)
    }

    private fun refresh(force: Boolean) {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                podcastsRepository.updatePodcasts(force)
            }
            // TODO: look at result of runCatching and show any errors

            refreshing.value = false
        }
    }

    fun onHomeCategorySelected(category: HomeCategory) {
        selectedCategory.value = category
    }

    fun onPodcastUnfollowed(podcastUri: String) {
        viewModelScope.launch {
            podcastStore.unfollowPodcast(podcastUri)
        }
    }
}

enum class HomeCategory {
    Library, Discover
}

data class HomeViewState(
    val featuredPodcasts: List<PodcastWithExtraInfo> = emptyList(),
    val refreshing: Boolean = false,
    val selectedHomeCategory: HomeCategory = HomeCategory.Discover,
    val homeCategories: List<HomeCategory> = emptyList(),
    val errorMessage: String? = null
)


