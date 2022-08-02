package com.yunxi.jetsurvey.util

/**
 * @author: created by wangkm
 * @time: 2022/07/25 12:13
 * @desc：
 * @email: 1240413544@qq.com
 */

data class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
