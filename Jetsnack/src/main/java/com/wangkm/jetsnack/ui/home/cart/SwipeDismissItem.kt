package com.wangkm.jetsnack.ui.home.cart

import androidx.compose.animation.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * @author: created by wangkm
 * @time: 2022/07/19 13:09
 * @desc：
 * @email: 1240413544@qq.com
 */

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
        /**
         * Holds the Swipe to dismiss composable, its animation and the current state
         */
fun SwipeDismissItem(
    modifier: Modifier = Modifier,
    directions: Set<DismissDirection> = setOf(DismissDirection.EndToStart),
    enter: EnterTransition = expandVertically(),
    exit: ExitTransition = shrinkVertically(),
    background: @Composable (offset: Dp) -> Unit,
    content: @Composable (isDismissed: Boolean) -> Unit,
) {
    // Hold the current state from the Swipe to Dismiss composable
    val dismissState = rememberDismissState()
    // Boolean value used for hiding the item if the current state is dismissed
    val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
    // Returns the swiped value in dp
    val offset = with(LocalDensity.current) { dismissState.offset.value.toDp() }

    AnimatedVisibility(
        modifier = modifier,
        visible = !isDismissed,
        enter = enter,
        exit = exit
    ) {
        SwipeToDismiss(
            modifier = modifier,
            state = dismissState,
            directions = directions,
            background = { background(offset) },
            dismissContent = { content(isDismissed) }
        )
    }
}

