package com.wangkm.jetchat.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

/**
 * @author: created by wangkm
 * @time: 2022/07/20 14:13
 * @desc：
 * @email: 1240413544@qq.com
 */

data class BaselineHeightModifier(
    val heightFromBaseline: Dp
) : LayoutModifier {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints

    ): MeasureResult {

        val textPlaceable = measurable.measure(constraints)
        val firstBaseline = textPlaceable[FirstBaseline]
        val lastBaseline = textPlaceable[LastBaseline]

        val height = heightFromBaseline.roundToPx() + lastBaseline - firstBaseline
        return layout(constraints.maxWidth, height) {
            val topY = heightFromBaseline.roundToPx() - firstBaseline
            textPlaceable.place(0, topY)
        }
    }
}

fun Modifier.baselineHeight(heightFromBaseline: Dp): Modifier =
    this.then(BaselineHeightModifier(heightFromBaseline))

