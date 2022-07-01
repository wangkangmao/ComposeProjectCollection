package com.wangkm.jetnews.data

import java.lang.Exception

/**
 * @author: created by wangkm
 * @time: 2022/06/27 12:57
 * @desc：
 * @email: 1240413544@qq.com
 */

/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}