package com.yunxi.crane.base

/**
 * @author: created by wangkm
 * @time: 2022/08/01 13:03
 * @desc：
 * @email: 1240413544@qq.com
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

