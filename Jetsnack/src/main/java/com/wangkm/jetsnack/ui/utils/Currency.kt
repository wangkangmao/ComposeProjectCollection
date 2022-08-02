package com.wangkm.jetsnack.ui.utils

import java.math.BigDecimal
import java.text.NumberFormat

/**
 * @author: created by wangkm
 * @time: 2022/07/19 12:31
 * @desc：
 * @email: 1240413544@qq.com
 */

fun formatPrice(price: Long): String {
    return NumberFormat.getCurrencyInstance().format(
        BigDecimal(price).movePointLeft(2)
    )
}