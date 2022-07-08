package com.wangkm.jetcaster.ui.home.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangkm.jetcaster.Graph
import com.wangkm.jetcaster.data.Category
import com.wangkm.jetcaster.data.CategoryStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * @author: created by wangkm
 * @time: 2022/07/08 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */

class DiscoverViewModel(
    private val categoryStore: CategoryStore = Graph.categoryStore
) : ViewModel() {
    // Holds our currently selected category
    private val _selectedCategory = MutableStateFlow<Category?>(null)

    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(DiscoverViewState())

    val state: StateFlow<DiscoverViewState>
        get() = _state

    init {
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.
            combine(
                categoryStore.categoriesSortedByPodcastCount()
                    .onEach { categories ->
                        // If we haven't got a selected category yet, select the first
                        if (categories.isNotEmpty() && _selectedCategory.value == null) {
                            _selectedCategory.value = categories[0]
                        }
                    },
                _selectedCategory
            ) { categories, selectedCategory ->
                DiscoverViewState(
                    categories = categories,
                    selectedCategory = selectedCategory
                )
            }.collect { _state.value = it }
        }
    }

    fun onCategorySelected(category: Category) {
        _selectedCategory.value = category
    }
}

data class DiscoverViewState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null
)


