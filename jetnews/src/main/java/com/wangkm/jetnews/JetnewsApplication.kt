package com.wangkm.jetnews

import android.app.Application
import com.wangkm.jetnews.data.AppContainer
import com.wangkm.jetnews.data.AppContainerImpl

/**
 * @author: created by wangkm
 * @time: 2022/06/27 12:26
 * @desc：
 * @email: 1240413544@qq.com
 */

class JetnewsApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}