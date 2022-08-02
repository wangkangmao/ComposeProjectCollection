package com.wangkm.jetcaster

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

/**
 * @author: created by wangkm
 * @time: 2022/07/08 13:18
 * @desc：
 * @email: 1240413544@qq.com
 */



/**
 * Application which sets up our dependency [Graph] with a context.
 */
class JetcasterApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            // Disable `Cache-Control` header support as some podcast images disable disk caching.
            .respectCacheHeaders(false)
            .build()
    }
}
