package com.yunxi.jetsurvey.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yunxi.jetsurvey.ui.theme.JetsurveyTheme
import com.yunxi.jetsurvey.R
import com.google.android.material.datepicker.MaterialDatePicker

/**
 * @author: created by wangkm
 * @time: 2022/07/25 13:10
 * @desc：
 * @email: 1240413544@qq.com
 */

class SurveyFragment : Fragment() {

    private val viewModel: SurveyViewModel by viewModels {
        SurveyViewModelFactory(PhotoUriManager(requireContext().applicationContext))
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { photoSaved ->
        if (photoSaved) {
            viewModel.onImageSaved()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.sign_in_fragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                JetsurveyTheme {
                    val state = viewModel.uiState.observeAsState().value ?: return@JetsurveyTheme
                    AnimatedContent(
                        targetState = state,
                        transitionSpec = {
                            fadeIn() + slideIntoContainer(
                                towards = AnimatedContentScope
                                    .SlideDirection.Up,
                                animationSpec = tween(ANIMATION_SLIDE_IN_DURATION)
                            ) with
                                    fadeOut(animationSpec = tween(ANIMATION_FADE_OUT_DURATION))
                        }
                    ) { targetState ->
                        // It's important to use targetState and not state, as its critical to ensure
                        // a successful lookup of all the incoming and outgoing content during
                        // content transform.
                        when (targetState) {
                            is SurveyState.Questions -> SurveyQuestionsScreen(
                                questions = targetState,
                                shouldAskPermissions = viewModel.askForPermissions,
                                onAction = { id, action -> handleSurveyAction(id, action) },
                                onDoNotAskForPermissions = { viewModel.doNotAskForPermissions() },
                                onDonePressed = { viewModel.computeResult(targetState) },
                                onBackPressed = {
                                    activity?.onBackPressedDispatcher?.onBackPressed()
                                }
                            )
                            is SurveyState.Result -> SurveyResultScreen(
                                result = targetState,
                                onDonePressed = {
                                    activity?.onBackPressedDispatcher?.onBackPressed()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSurveyAction(questionId: Int, actionType: SurveyActionType) {
        when (actionType) {
            SurveyActionType.PICK_DATE -> showDatePicker(questionId)
            SurveyActionType.TAKE_PHOTO -> takeAPhoto()
            SurveyActionType.SELECT_CONTACT -> selectContact(questionId)
        }
    }

    private fun showDatePicker(questionId: Int) {
        val date = viewModel.getCurrentDate(questionId = questionId)
        val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(date)
            .build()
        picker.show(requireActivity().supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            picker.selection?.let { selectedDate ->
                viewModel.onDatePicked(questionId, selectedDate)
            }
        }
    }

    private fun takeAPhoto() {
        takePicture.launch(viewModel.getUriToSaveImage())
    }

    @Suppress("UNUSED_PARAMETER")
    private fun selectContact(questionId: Int) {
        // TODO: unsupported for now
    }

    companion object {
        private const val ANIMATION_SLIDE_IN_DURATION = 600
        private const val ANIMATION_FADE_OUT_DURATION = 200
    }
}
