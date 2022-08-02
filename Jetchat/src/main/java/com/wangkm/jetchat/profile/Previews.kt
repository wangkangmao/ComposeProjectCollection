package com.wangkm.jetchat.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.jetchat.theme.JetchatTheme
import com.wangkm.jetchat.data.colleagueProfile
import com.wangkm.jetchat.data.meProfile

/**
 * @author: created by wangkm
 * @time: 2022/07/21 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */



@Preview(widthDp = 340, name = "340 width - Me")
@Composable
fun ProfilePreview340() {
    JetchatTheme {
        ProfileScreen(meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Me")
@Composable
fun ProfilePreview480Me() {
    JetchatTheme {
        ProfileScreen(meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Other")
@Composable
fun ProfilePreview480Other() {
    JetchatTheme {
        ProfileScreen(colleagueProfile)
    }
}
@Preview(widthDp = 340, name = "340 width - Me - Dark")
@Composable
fun ProfilePreview340MeDark() {
    JetchatTheme(isDarkTheme = true) {
        ProfileScreen(meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Me - Dark")
@Composable
fun ProfilePreview480MeDark() {
    JetchatTheme(isDarkTheme = true) {
        ProfileScreen(meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Other - Dark")
@Composable
fun ProfilePreview480OtherDark() {
    JetchatTheme(isDarkTheme = true) {
        ProfileScreen(colleagueProfile)
    }
}

