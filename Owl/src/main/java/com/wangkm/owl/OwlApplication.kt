package com.wangkm.owl

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
import com.wangkm.owl.ui.utils.UnsplashSizingInterceptor

/**
 * @author: created by wangkm
 * @time: 2022/07/28 12:14
 * @desc：
 * @email: 1240413544@qq.com
 */

@Suppress("unused")
class OwlApplication : Application(), ImageLoaderFactory {

    /**
     * Create the singleton [ImageLoader].
     * This is used by [AsyncImage] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            .respectCacheHeaders(false)
            .build()
    }
}