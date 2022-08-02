package com.yunxi.crane.util

import coil.intercept.Interceptor
import coil.request.ImageResult
import coil.size.pxOrElse
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * @author: created by wangkm
 * @time: 2022/08/02 13:05
 * @desc：
 * @email: 1240413544@qq.com
 */

object UnsplashSizingInterceptor : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        val widthPx = chain.size.width.pxOrElse { -1 }
        val heightPx = chain.size.height.pxOrElse { -1 }
        if (widthPx > 0 && heightPx > 0 && data is String &&
            data.startsWith("https://images.unsplash.com/photo-")
        ) {
            val url = data.toHttpUrl()
                .newBuilder()
                .addQueryParameter("w", widthPx.toString())
                .addQueryParameter("h", heightPx.toString())
                .build()
            val request = chain.request.newBuilder().data(url).build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request)
    }
}