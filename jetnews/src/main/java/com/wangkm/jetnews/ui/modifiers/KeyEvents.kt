package com.wangkm.jetnews.ui.modifiers

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*

/**
 * @author: created by wangkm
 * @time: 2022/06/30 13:15
 * @desc：
 * @email: 1240413544@qq.com
 */

/**
 * Intercepts a key event rather than passing it on to children
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.interceptKey(key: Key, onKeyEvent: () -> Unit): Modifier {
    return this.onPreviewKeyEvent {
        if (it.key == key && it.type == KeyEventType.KeyUp) { // fire onKeyEvent on KeyUp to prevent duplicates
            onKeyEvent()
            true
        } else it.key == key // only pass the key event to children if it's not the chosen key
    }
}

