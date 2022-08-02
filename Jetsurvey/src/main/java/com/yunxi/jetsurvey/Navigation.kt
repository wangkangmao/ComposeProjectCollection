package com.yunxi.jetsurvey

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.InvalidParameterException

/**
 * @author: created by wangkm
 * @time: 2022/07/25 12:12
 * @desc：
 * @email: 1240413544@qq.com
 */

enum class Screen { Welcome, SignUp, SignIn, Survey }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Welcome -> {
            findNavController().navigate(R.id.welcome_fragment)
        }
        Screen.SignUp -> {
            findNavController().navigate(R.id.sign_up_fragment)
        }
        Screen.SignIn -> {
            findNavController().navigate(R.id.sign_in_fragment)
        }
        Screen.Survey -> {
            findNavController().navigate(R.id.survey_fragment)
        }
    }
}