package com.wangkm.owl.ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * @author: created by wangkm
 * @time: 2022/07/28 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */

/**
 * A [Modifier] which draws a vertical gradient
 */
fun Modifier.scrim(colors: List<Color>): Modifier = drawWithContent {
    drawContent()
    drawRect(Brush.verticalGradient(colors))
}
