package com.wangkm.jetcaster.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wangkm.jetcaster.R
import com.wangkm.jetcaster.ui.home.Home
import com.wangkm.jetcaster.ui.player.PlayerScreen
import com.wangkm.jetcaster.ui.player.PlayerViewModel
import com.wangkm.jetcaster.util.DevicePosture
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: created by wangkm
 * @time: 2022/07/06 13:18
 * @desc：
 * @email: 1240413544@qq.com
 */


@Composable
fun JetcasterApp(
    devicePosture: StateFlow<DevicePosture>,
    appState: JetcasterAppState = rememberJetcasterAppState()
) {
    if (appState.isOnline) {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) { backStackEntry ->
                Home(
                    navigateToPlayer = { episodeUri ->
                        appState.navigateToPlayer(episodeUri, backStackEntry)
                    }
                )
            }
            composable(Screen.Player.route) { backStackEntry ->
                val playerViewModel: PlayerViewModel = viewModel(
                    factory = PlayerViewModel.provideFactory(
                        owner = backStackEntry,
                        defaultArgs = backStackEntry.arguments
                    )
                )
                PlayerScreen(playerViewModel, devicePosture, onBackPress = appState::navigateBack)
            }
        }
    } else {
        OfflineDialog { appState.refreshOnline() }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.connection_error_title)) },
        text = { Text(text = stringResource(R.string.connection_error_message)) },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry_label))
            }
        }
    )
}

