package com.wangkm.jetchat.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.compose.jetchat.theme.JetchatTheme

/**
 * @author: created by wangkm
 * @time: 2022/07/20 13:09
 * @desc：
 * @email: 1240413544@qq.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetchatScaffold(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onProfileClicked: (String) -> Unit,
    onChatClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    JetchatTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                JetchatDrawer(
                    onProfileClicked = onProfileClicked,
                    onChatClicked = onChatClicked
                )
            },
            content = content
        )
    }
}

