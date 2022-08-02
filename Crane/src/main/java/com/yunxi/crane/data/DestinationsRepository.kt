package com.yunxi.crane.data

import javax.inject.Inject

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:13
 * @desc：
 * @email: 1240413544@qq.com
 */

class DestinationsRepository @Inject constructor(
    private val destinationsLocalDataSource: DestinationsLocalDataSource
) {
    val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
    val hotels: List<ExploreModel> = destinationsLocalDataSource.craneHotels
    val restaurants: List<ExploreModel> = destinationsLocalDataSource.craneRestaurants

    fun getDestination(cityName: String): ExploreModel? {
        return destinationsLocalDataSource.craneDestinations.firstOrNull {
            it.city.name == cityName
        }
    }
}
