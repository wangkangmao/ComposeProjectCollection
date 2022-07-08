package com.wangkm.jetcaster.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


@Composable
fun JetcasterTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = JetcasterColors,
        typography = JetcasterTypography,
        shapes = JetcasterShapes,
        content = content
    )
}