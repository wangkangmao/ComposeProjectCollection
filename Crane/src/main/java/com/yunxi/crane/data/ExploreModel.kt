package com.yunxi.crane.data

import androidx.compose.runtime.Immutable

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:12
 * @desc：
 * @email: 1240413544@qq.com
 */

@Immutable
data class City(
    val name: String,
    val country: String,
    val latitude: String,
    val longitude: String
) {
    val nameToDisplay = "$name, $country"
}

@Immutable
data class ExploreModel(
    val city: City,
    val description: String,
    val imageUrl: String
)
