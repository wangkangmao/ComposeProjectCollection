package com.wangkm.jetnews.utils

import androidx.compose.foundation.lazy.LazyListState

/**
 * @author: created by wangkm
 * @time: 2022/06/30 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0
