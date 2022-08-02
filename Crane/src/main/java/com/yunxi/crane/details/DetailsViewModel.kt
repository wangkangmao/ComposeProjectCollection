package com.yunxi.crane.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yunxi.crane.data.DestinationsRepository
import com.yunxi.crane.data.ExploreModel
import com.yunxi.crane.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author: created by wangkm
 * @time: 2022/08/01 13:02
 * @desc：
 * @email: 1240413544@qq.com
 */

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cityName = savedStateHandle.get<String>(KEY_ARG_DETAILS_CITY_NAME)!!

    val cityDetails: Result<ExploreModel>
        get() {
            val destination = destinationsRepository.getDestination(cityName)
            return if (destination != null) {
                Result.Success(destination)
            } else {
                Result.Error(IllegalArgumentException("City doesn't exist"))
            }
        }
}