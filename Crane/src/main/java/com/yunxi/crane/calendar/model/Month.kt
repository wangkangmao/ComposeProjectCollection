package com.yunxi.crane.calendar.model

import java.time.YearMonth

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:23
 * @desc：
 * @email: 1240413544@qq.com
 */

data class Month(
    val yearMonth: YearMonth,
    val weeks: List<Week>
)
