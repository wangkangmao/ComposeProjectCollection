package com.wangkm.jetnews.data

import android.content.Context
import com.wangkm.jetnews.data.interests.InterestsRepository
import com.wangkm.jetnews.data.interests.impl.FakeInterestsRepository
import com.wangkm.jetnews.data.posts.PostsRepository
import com.wangkm.jetnews.data.posts.impl.FakePostsRepository

/**
 * @author: created by wangkm
 * @time: 2022/06/27 12:27
 * @desc：
 * @email: 1240413544@qq.com
 */

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val postsRepository: PostsRepository
    val interestsRepository: InterestsRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy{
        FakePostsRepository()
    }
    override val interestsRepository: InterestsRepository by lazy{
        FakeInterestsRepository()
    }
}