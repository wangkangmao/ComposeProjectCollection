package com.yunxi.crane.util

import com.yunxi.crane.calendar.CALENDAR_STARTS_ON
import java.time.YearMonth
import java.time.temporal.WeekFields

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:21
 * @desc：
 * @email: 1240413544@qq.com
 */

fun YearMonth.getNumberWeeks(weekFields: WeekFields = CALENDAR_STARTS_ON): Int {
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]
    return lastWeekNumber - firstWeekNumber + 1 // Both weeks inclusive
}


