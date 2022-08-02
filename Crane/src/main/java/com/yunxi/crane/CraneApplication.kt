package com.yunxi.crane

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
import com.yunxi.crane.util.UnsplashSizingInterceptor
import dagger.hilt.android.HiltAndroidApp

/**
 * @author: created by wangkm
 * @time: 2022/07/29 12:12
 * @desc：
 * @email: 1240413544@qq.com
 */
@HiltAndroidApp
class CraneApplication : Application(), ImageLoaderFactory {

    /**
     * Create the singleton [ImageLoader].
     * This is used by [AsyncImage] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            .build()
    }
}
