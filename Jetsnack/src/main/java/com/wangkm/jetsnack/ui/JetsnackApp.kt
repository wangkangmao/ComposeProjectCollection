package com.wangkm.jetsnack.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.AlertDialog
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wangkm.jetsnack.ui.components.JetsnackScaffold
import com.wangkm.jetsnack.ui.components.JetsnackSnackbar
import com.wangkm.jetsnack.ui.home.HomeSections
import com.wangkm.jetsnack.ui.home.JetsnackBottomBar
import com.wangkm.jetsnack.ui.home.addHomeGraph
import com.wangkm.jetsnack.ui.snackdetail.SnackDetail
import com.wangkm.jetsnack.ui.theme.JetsnackTheme
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: created by wangkm
 * @time: 2022/07/12 12:06
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
fun JetsnackApp() {
    JetsnackTheme {
        val appState = rememberJetsnackAppState()
        JetsnackScaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    JetsnackBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> JetsnackSnackbar(snackbarData) }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.HOME_ROUTE,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                jetsnackNavGraph(
                    onSnackSelected = appState::navigateToSnackDetail,
                    upPress = appState::upPress
                )
            }
        }
    }
}

private fun NavGraphBuilder.jetsnackNavGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {

    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(onSnackSelected)
    }
    composable(
        "${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val snackId = arguments.getLong(MainDestinations.SNACK_ID_KEY)
        SnackDetail(snackId, upPress)
    }
}


