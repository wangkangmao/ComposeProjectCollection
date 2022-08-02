package com.wangkm.owl.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author: created by wangkm
 * @time: 2022/07/27 12:29
 * @desc：
 * @email: 1240413544@qq.com
 */

@Immutable
data class Elevations(val card: Dp = 0.dp)

internal val LocalElevations = staticCompositionLocalOf { Elevations() }