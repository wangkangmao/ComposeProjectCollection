package com.yunxi.crane.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunxi.crane.SplashState
import com.yunxi.crane.calendar.model.CalendarState
import com.yunxi.crane.data.DestinationsRepository
import com.yunxi.crane.data.ExploreModel
import com.yunxi.crane.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:12
 * @desc：
 * @email: 1240413544@qq.com
 */


const val MAX_PEOPLE = 4

@HiltViewModel
class MainViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val shownSplash = mutableStateOf(SplashState.Shown)
    val hotels: List<ExploreModel> = destinationsRepository.hotels
    val restaurants: List<ExploreModel> = destinationsRepository.restaurants

    val calendarState = CalendarState()

    private val _suggestedDestinations = MutableLiveData<List<ExploreModel>>()
    val suggestedDestinations: LiveData<List<ExploreModel>>
        get() = _suggestedDestinations

    init {
        _suggestedDestinations.value = destinationsRepository.destinations
    }

    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            calendarState.setSelectedDay(daySelected)
        }
    }

    fun updatePeople(people: Int) {
        viewModelScope.launch {
            if (people > MAX_PEOPLE) {
                _suggestedDestinations.value = emptyList()
            } else {
                val newDestinations = withContext(defaultDispatcher) {
                    destinationsRepository.destinations
                        .shuffled(Random(people * (1..100).shuffled().first()))
                }
                _suggestedDestinations.value = newDestinations
            }
        }
    }

    fun toDestinationChanged(newDestination: String) {
        viewModelScope.launch {
            val newDestinations = withContext(defaultDispatcher) {
                destinationsRepository.destinations
                    .filter { it.city.nameToDisplay.contains(newDestination) }
            }
            _suggestedDestinations.value = newDestinations
        }
    }
}
