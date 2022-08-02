package com.wangkm.jetsnack.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wangkm.jetsnack.ui.theme.JetsnackTheme

/**
 * @author: created by wangkm
 * @time: 2022/07/19 14:17
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
fun JetsnackSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = JetsnackTheme.colors.uiBackground,
    contentColor: Color = JetsnackTheme.colors.textSecondary,
    actionColor: Color = JetsnackTheme.colors.brand,
    elevation: Dp = 6.dp
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        actionColor = actionColor,
        elevation = elevation
    )
}

