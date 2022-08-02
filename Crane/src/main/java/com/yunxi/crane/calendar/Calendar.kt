package com.yunxi.crane.calendar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yunxi.crane.calendar.model.*
import com.yunxi.crane.calendar.model.DURATION_MILLIS_PER_DAY
import com.yunxi.crane.ui.CraneTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:21
 * @desc：
 * @email: 1240413544@qq.com
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Calendar(
    calendarState: CalendarState,
    onDayClicked: (date: LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val calendarUiState = calendarState.calendarUiState.value
    val numberSelectedDays = calendarUiState.numberSelectedDays.toInt()

    val selectedAnimationPercentage = remember(numberSelectedDays) {
        Animatable(0f)
    }
    // Start a Launch Effect when the number of selected days change.
    // using .animateTo() we animate the percentage selection from 0f - 1f
    LaunchedEffect(numberSelectedDays) {
        if (calendarUiState.hasSelectedDates) {

            val animationSpec: TweenSpec<Float> = tween(
                durationMillis =
                (numberSelectedDays.coerceAtLeast(0) * DURATION_MILLIS_PER_DAY)
                    .coerceAtMost(2000),
                easing = EaseOutQuart
            )
            selectedAnimationPercentage.animateTo(
                targetValue = 1f,
                animationSpec = animationSpec
            )
        }
    }

    LazyColumn(
        modifier = modifier.consumedWindowInsets(contentPadding),
        contentPadding = contentPadding
    ) {
        calendarState.listMonths.forEach { month ->
            itemsCalendarMonth(calendarUiState, onDayClicked, { selectedAnimationPercentage.value }, month)
        }

        item(key = "bottomSpacer") {
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    WindowInsets.navigationBars
                )
            )
        }
    }
}

private fun LazyListScope.itemsCalendarMonth(
    calendarUiState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    selectedPercentageProvider: () -> Float,
    month: Month
) {
    item(month.yearMonth.month.name + month.yearMonth.year + "header") {
        MonthHeader(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp),
            month = month.yearMonth.month.name,
            year = month.yearMonth.year.toString()
        )
    }

    // Expanding width and centering horizontally
    val contentModifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
    item(month.yearMonth.month.name + month.yearMonth.year + "daysOfWeek") {
        DaysOfWeek(modifier = contentModifier)
    }

    // A custom key needs to be given to these items so that they can be found in tests that
    // need scrolling. The format of the key is ${year/month/weekNumber}. Thus,
    // the key for the fourth week of December 2020 is "2020/12/4"
    itemsIndexed(month.weeks, key = { index, _ ->
        month.yearMonth.year.toString() + "/" + month.yearMonth.month.value + "/" + (index + 1).toString()
    }) { _, week ->
        val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
        val currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        if (calendarUiState.hasSelectedPeriodOverlap(currentDay, currentDay.plusDays(6))) {
            WeekSelectionPill(
                state = calendarUiState,
                currentWeekStart = currentDay,
                widthPerDay = CELL_SIZE,
                week = week,
                selectedPercentageTotalProvider = selectedPercentageProvider
            )
        }
        Week(
            calendarUiState = calendarUiState,
            modifier = contentModifier,
            week = week,
            onDayClicked = onDayClicked
        )
        Spacer(Modifier.height(8.dp))
    }
}

internal val CALENDAR_STARTS_ON = WeekFields.ISO

@Preview
@Composable
fun DayPreview() {
    CraneTheme {
        Calendar(CalendarState(), onDayClicked = { })
    }
}

