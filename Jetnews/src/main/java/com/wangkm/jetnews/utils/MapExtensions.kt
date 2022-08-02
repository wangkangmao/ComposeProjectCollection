package com.wangkm.jetnews.utils

/**
 * @author: created by wangkm
 * @time: 2022/06/27 13:03
 * @desc：
 * @email: 1240413544@qq.com
 */

internal fun <E> MutableSet<E>.addOrRemove(element:E){
    if (!add(element)) {
        remove(element)
    }
}