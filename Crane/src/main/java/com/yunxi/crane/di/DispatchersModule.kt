package com.yunxi.crane.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:15
 * @desc：
 * @email: 1240413544@qq.com
 */

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher
