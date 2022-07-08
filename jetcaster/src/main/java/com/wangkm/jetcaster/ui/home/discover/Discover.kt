package com.wangkm.jetcaster.ui.home.discover

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wangkm.jetcaster.data.Category
import com.wangkm.jetcaster.ui.home.category.PodcastCategory
import com.wangkm.jetcaster.ui.theme.Keyline1

/**
 * @author: created by wangkm
 * @time: 2022/07/08 12:23
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
fun Discover(
    navigateToPlayer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: DiscoverViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    val selectedCategory = viewState.selectedCategory

    if (viewState.categories.isNotEmpty() && selectedCategory != null) {
        Column(modifier) {
            Spacer(Modifier.height(8.dp))

            PodcastCategoryTabs(
                categories = viewState.categories,
                selectedCategory = selectedCategory,
                onCategorySelected = viewModel::onCategorySelected,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Crossfade(
                targetState = selectedCategory,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { category ->
                /**
                 * TODO, need to think about how this will scroll within the outer VerticalScroller
                 */
                /**
                 * TODO, need to think about how this will scroll within the outer VerticalScroller
                 */
                /**
                 * TODO, need to think about how this will scroll within the outer VerticalScroller
                 */

                /**
                 * TODO, need to think about how this will scroll within the outer VerticalScroller
                 */
                PodcastCategory(
                    categoryId = category.id,
                    navigateToPlayer = navigateToPlayer,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
    // TODO: empty state
}

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
private fun PodcastCategoryTabs(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {}, /* Disable the built-in divider */
        edgePadding = Keyline1,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) }
            ) {
                ChoiceChipContent(
                    text = category.name,
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun ChoiceChipContent(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary.copy(alpha = 0.08f)
            else -> MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        },
        contentColor = when {
            selected -> MaterialTheme.colors.primary
            else -> MaterialTheme.colors.onSurface
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
