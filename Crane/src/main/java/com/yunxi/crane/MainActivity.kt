package com.yunxi.crane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yunxi.crane.calendar.CalendarScreen
import com.yunxi.crane.details.launchDetailsActivity
import com.yunxi.crane.home.CraneHome
import com.yunxi.crane.home.LandingScreen
import com.yunxi.crane.home.MainViewModel
import com.yunxi.crane.home.OnExploreItemClicked
import com.yunxi.crane.ui.CraneTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CraneTheme {
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Home.route) {
                    composable(Routes.Home.route) {
                        val mainViewModel = hiltViewModel<MainViewModel>()
                        MainScreen(
                            widthSize = widthSizeClass,
                            onExploreItemClicked = {
                                launchDetailsActivity(context = this@MainActivity, item = it)
                            },
                            onDateSelectionClicked = {
                                navController.navigate(Routes.Calendar.route)
                            },
                            mainViewModel = mainViewModel
                        )
                    }
                    composable(Routes.Calendar.route) {
                        val parentEntry = remember {
                            navController.getBackStackEntry(Routes.Home.route)
                        }
                        val parentViewModel = hiltViewModel<MainViewModel>(
                            parentEntry
                        )
                        CalendarScreen(onBackPressed = {
                            navController.popBackStack()
                        }, mainViewModel = parentViewModel)
                    }
                }
            }
        }
    }
}

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Calendar : Routes("calendar")
}

@VisibleForTesting
@Composable
fun MainScreen(
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    Surface(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        color = MaterialTheme.colors.primary
    ) {
        val transitionState = remember { MutableTransitionState(mainViewModel.shownSplash.value) }
        val transition = updateTransition(transitionState, label = "splashTransition")
        val splashAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 100) }, label = "splashAlpha"
        ) {
            if (it == SplashState.Shown) 1f else 0f
        }
        val contentAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 300) }, label = "contentAlpha"
        ) {
            if (it == SplashState.Shown) 0f else 1f
        }
        val contentTopPadding by transition.animateDp(
            transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = "contentTopPadding"
        ) {
            if (it == SplashState.Shown) 100.dp else 0.dp
        }

        Box {
            LandingScreen(
                modifier = Modifier.alpha(splashAlpha),
                onTimeout = {
                    transitionState.targetState = SplashState.Completed
                    mainViewModel.shownSplash.value = SplashState.Completed
                }
            )

            MainContent(
                modifier = Modifier.alpha(contentAlpha),
                topPadding = contentTopPadding,
                widthSize = widthSize,
                onExploreItemClicked = onExploreItemClicked,
                onDateSelectionClicked = onDateSelectionClicked,
                viewModel = mainViewModel
            )
        }
    }
}


@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp,
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    viewModel: MainViewModel
) {

    Column(modifier = modifier) {
        Spacer(Modifier.padding(top = topPadding))
        CraneHome(
            widthSize = widthSize,
            modifier = modifier,
            onExploreItemClicked = onExploreItemClicked,
            onDateSelectionClicked = onDateSelectionClicked,
            viewModel = viewModel
        )
    }
}

enum class SplashState { Shown, Completed }

