package com.yunxi.crane.calendar.model

/**
 * @author: created by wangkm
 * @time: 2022/08/01 12:56
 * @desc：
 * @email: 1240413544@qq.com
 */

enum class AnimationDirection {
    FORWARDS,
    BACKWARDS;

    fun isBackwards() = this == BACKWARDS
    fun isForwards() = this == FORWARDS
}

