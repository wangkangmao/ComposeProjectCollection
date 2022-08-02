package com.yunxi.crane.calendar.model

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import java.time.LocalDate
import java.time.YearMonth
import  com.yunxi.crane.R
import com.yunxi.crane.calendar.CELL_SIZE

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
internal fun DayOfWeekHeading(day: String) {
    DayContainer {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            text = day,
            style = MaterialTheme.typography.caption.copy(Color.White.copy(alpha = 0.6f))
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = { },
    onClickEnabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    onClickLabel: String? = null,
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel = stringResource(
        if (selected) R.string.state_descr_selected else R.string.state_descr_not_selected
    )
    Box(
        modifier = modifier
            .size(width = CELL_SIZE, height = CELL_SIZE)
            .pointerInput(Any()) {
                detectTapGestures {
                    onClick()
                }
            }
            .then(
                if (onClickEnabled) {
                    modifier.semantics {
                        stateDescription = stateDescriptionLabel
                        onClick(label = onClickLabel, action = null)
                    }
                } else {
                    modifier.clearAndSetSemantics { }
                }
            )
            .background(backgroundColor)
    ) {
        content()
    }
}

@Composable
internal fun Day(
    day: LocalDate,
    calendarState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    month: YearMonth,
    modifier: Modifier = Modifier
) {
    val selected = calendarState.isDateInSelectedPeriod(day)
    DayContainer(
        modifier = modifier.semantics {
            text = AnnotatedString("${month.month.name.lowercase().capitalize(Locale.current)} ${day.dayOfMonth} ${month.year}")
            dayStatusProperty = selected
        },
        selected = selected,
        onClick = { onDayClicked(day) },
        onClickLabel = stringResource(id = R.string.click_label_select)
    ) {

        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                // Parent will handle semantics
                .clearAndSetSemantics {},
            text = day.dayOfMonth.toString(),
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )
    }
}

val DayStatusKey = SemanticsPropertyKey<Boolean>("DayStatusKey")
var SemanticsPropertyReceiver.dayStatusProperty by DayStatusKey