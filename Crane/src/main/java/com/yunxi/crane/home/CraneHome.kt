package com.yunxi.crane.home

import androidx.compose.animation.*
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yunxi.crane.R
import com.yunxi.crane.base.CraneDrawer
import com.yunxi.crane.base.CraneTabBar
import com.yunxi.crane.base.CraneTabs
import com.yunxi.crane.data.ExploreModel
import com.yunxi.crane.ui.BottomSheetShape
import kotlinx.coroutines.launch

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:11
 * @desc：
 * @email: 1240413544@qq.com
 */

typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class CraneScreen {
    Fly, Sleep, Eat
}

@Composable
fun CraneHome(
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.statusBarsPadding(),
        drawerContent = {
            CraneDrawer()
        }
    ) { contentPadding ->
        val scope = rememberCoroutineScope()
        CraneHomeContent(
            modifier = modifier.padding(contentPadding),
            widthSize = widthSize,
            onExploreItemClicked = onExploreItemClicked,
            onDateSelectionClicked = onDateSelectionClicked,
            openDrawer = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun CraneHomeContent(
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val suggestedDestinations by viewModel.suggestedDestinations.observeAsState()

    val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
    var tabSelected by remember { mutableStateOf(CraneScreen.Fly) }

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerShape = BottomSheetShape,
        frontLayerScrimColor = Color.Unspecified,
        appBar = {
            HomeTabBar(openDrawer, tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            SearchContent(
                widthSize,
                tabSelected,
                viewModel,
                onPeopleChanged,
                onDateSelectionClicked,
                onExploreItemClicked
            )
        },
        frontLayerContent = {
            AnimatedContent(
                targetState = tabSelected,
                transitionSpec = {
                    val direction = if (initialState.ordinal < targetState.ordinal)
                        AnimatedContentScope.SlideDirection.Left else AnimatedContentScope
                        .SlideDirection.Right

                    slideIntoContainer(
                        towards = direction,
                        animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION)
                    ) with
                            slideOutOfContainer(
                                towards = direction,
                                animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION)
                            ) using SizeTransform(
                        clip = false,
                        sizeAnimationSpec = { _, _ ->
                            tween(ANIMATED_CONTENT_ANIMATION_DURATION, easing = EaseInOut)
                        }
                    )
                }
            ) { targetState ->
                when (targetState) {
                    CraneScreen.Fly -> {
                        suggestedDestinations?.let { destinations ->
                            ExploreSection(
                                widthSize = widthSize,
                                title = stringResource(R.string.explore_flights_by_destination),
                                exploreList = destinations,
                                onItemClicked = onExploreItemClicked
                            )
                        }
                    }
                    CraneScreen.Sleep -> {
                        ExploreSection(
                            widthSize = widthSize,
                            title = stringResource(R.string.explore_properties_by_destination),
                            exploreList = viewModel.hotels,
                            onItemClicked = onExploreItemClicked
                        )
                    }
                    CraneScreen.Eat -> {
                        ExploreSection(
                            widthSize = widthSize,
                            title = stringResource(R.string.explore_restaurants_by_destination),
                            exploreList = viewModel.restaurants,
                            onItemClicked = onExploreItemClicked
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ExploreSection(widthSize: WindowWidthSizeClass, title: String, exploreList: List<ExploreModel>, onItemClicked: (ExploreModel) -> Unit) {

}

@Composable
private fun HomeTabBar(
    openDrawer: () -> Unit,
    tabSelected: CraneScreen,
    onTabSelected: (CraneScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    CraneTabBar(
        modifier = modifier
            .wrapContentWidth()
            .sizeIn(maxWidth = 500.dp),
        onMenuClicked = openDrawer
    ) { tabBarModifier ->
        CraneTabs(
            modifier = tabBarModifier,
            titles = CraneScreen.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab -> onTabSelected(CraneScreen.values()[newTab.ordinal]) }
        )
    }
}

private const val ANIMATED_CONTENT_ANIMATION_DURATION = 600
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchContent(
    widthSize: WindowWidthSizeClass,
    tabSelected: CraneScreen,
    viewModel: MainViewModel,
    onPeopleChanged: (Int) -> Unit,
    onDateSelectionClicked: () -> Unit,
    onExploreItemClicked: OnExploreItemClicked
) {
    // Reading datesSelected State from here instead of passing the String from the ViewModel
    // to cause a recomposition when the dates change.
    val selectedDates = viewModel.calendarState.calendarUiState.value.selectedDatesFormatted
    AnimatedContent(
        targetState = tabSelected,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION, easing = EaseIn)
            ).with(
                fadeOut(
                    animationSpec = tween(ANIMATED_CONTENT_ANIMATION_DURATION, easing = EaseOut)
                )
            ).using(
                SizeTransform(
                    sizeAnimationSpec = { _, _ ->
                        tween(ANIMATED_CONTENT_ANIMATION_DURATION, easing = EaseInOut)
                    }
                )
            )
        },
    ) { targetState ->
        when (targetState) {
            CraneScreen.Fly -> FlySearchContent(
                widthSize = widthSize,
                datesSelected = selectedDates,
                searchUpdates = FlySearchContentUpdates(
                    onPeopleChanged = onPeopleChanged,
                    onToDestinationChanged = { viewModel.toDestinationChanged(it) },
                    onDateSelectionClicked = onDateSelectionClicked,
                    onExploreItemClicked = onExploreItemClicked
                )
            )
            CraneScreen.Sleep -> SleepSearchContent(
                widthSize = widthSize,
                datesSelected = selectedDates,
                sleepUpdates = SleepSearchContentUpdates(
                    onPeopleChanged = onPeopleChanged,
                    onDateSelectionClicked = onDateSelectionClicked,
                    onExploreItemClicked = onExploreItemClicked
                )
            )
            CraneScreen.Eat -> EatSearchContent(
                widthSize = widthSize,
                datesSelected = selectedDates,
                eatUpdates = EatSearchContentUpdates(
                    onPeopleChanged = onPeopleChanged,
                    onDateSelectionClicked = onDateSelectionClicked,
                    onExploreItemClicked = onExploreItemClicked
                )
            )
        }
    }
}

data class FlySearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onToDestinationChanged: (String) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)

data class SleepSearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)

data class EatSearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)
