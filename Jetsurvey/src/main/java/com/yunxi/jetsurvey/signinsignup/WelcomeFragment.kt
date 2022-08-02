package com.yunxi.jetsurvey.signinsignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yunxi.jetsurvey.ui.theme.JetsurveyTheme
import com.yunxi.jetsurvey.Screen
import com.yunxi.jetsurvey.navigate

/**
 * @author: created by wangkm
 * @time: 2022/07/22 13:18
 * @desc：
 * @email: 1240413544@qq.com
 */

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels { WelcomeViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.Welcome)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                JetsurveyTheme {
                    WelcomeScreen(
                        onEvent = { event ->
                            when (event) {
                                is WelcomeEvent.SignInSignUp -> viewModel.handleContinue(
                                    event.email
                                )
                                WelcomeEvent.SignInAsGuest -> viewModel.signInAsGuest()
                            }
                        }
                    )
                }
            }
        }

    }
}
