package com.wangkm.jetchat

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: created by wangkm
 * @time: 2022/07/20 13:18
 * @desc：
 * @email: 1240413544@qq.com
 */

class MainViewModel : ViewModel() {

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened: StateFlow<Boolean> = _drawerShouldBeOpened

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }
    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }
}
