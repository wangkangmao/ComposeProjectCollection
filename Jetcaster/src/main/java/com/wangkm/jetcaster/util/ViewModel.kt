package com.wangkm.jetcaster.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author: created by wangkm
 * @time: 2022/07/08 12:21
 * @desc：
 * @email: 1240413544@qq.com
 */


/**
 * Returns a [ViewModelProvider.Factory] which will return the result of [create] when it's
 * [ViewModelProvider.Factory.create] function is called.
 *
 * If the created [ViewModel] does not match the requested class, an [IllegalArgumentException]
 * exception is thrown.
 */
fun <VM : ViewModel> viewModelProviderFactoryOf(
    create: () -> VM
): ViewModelProvider.Factory = SimpleFactory(create)

/**
 * This needs to be a named class currently to workaround a compiler issue: b/163807311
 */
private class SimpleFactory<VM : ViewModel>(
    private val create: () -> VM
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm = create()
        if (modelClass.isInstance(vm)) {
            @Suppress("UNCHECKED_CAST")
            return vm as T
        }
        throw IllegalArgumentException("Can not create ViewModel for class: $modelClass")
    }
}


