package com.wangkm.owl.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @author: created by wangkm
 * @time: 2022/07/27 12:29
 * @desc：
 * @email: 1240413544@qq.com
 */

@Immutable
data class Images(@DrawableRes val lockupLogo: Int)

internal val LocalImages = staticCompositionLocalOf<Images> {
    error("No LocalImages specified")
}

