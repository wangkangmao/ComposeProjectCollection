package com.yunxi.jetsurvey.signinsignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yunxi.jetsurvey.ui.theme.JetsurveyTheme
import com.yunxi.jetsurvey.R
import com.yunxi.jetsurvey.Screen
import com.yunxi.jetsurvey.navigate

/**
 * @author: created by wangkm
 * @time: 2022/07/25 12:56
 * @desc：
 * @email: 1240413544@qq.com
 */

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.SignUp)
            }
        }

        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.sign_up_fragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                JetsurveyTheme {
                    SignUp(
                        onNavigationEvent = { event ->
                            when (event) {
                                is SignUpEvent.SignUp -> {

                                    viewModel.signUp(event.email, event.password)
                                }
                                SignUpEvent.SignIn -> {
                                    viewModel.signIn()
                                }
                                SignUpEvent.SignInAsGuest -> {
                                    viewModel.signInAsGuest()
                                }
                                SignUpEvent.NavigateBack -> {
                                    activity?.onBackPressedDispatcher?.onBackPressed()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

