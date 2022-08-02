package com.wangkm.jetnews.ui.interests

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * @author: created by wangkm
 * @time: 2022/07/01 12:29
 * @desc：  Stateful composable that displays the Navigation route for the Interests screen.
 * @param interestsViewModel ViewModel that handles the business logic of this screen
 * @param isExpandedScreen (state) true if the screen is expanded
 * @param openDrawer (event) request opening the app drawer
 * @param scaffoldState (state) state for screen Scaffold
 * @email: 1240413544@qq.com
 */


@Composable
fun InterestsRoute (
    interestsViewModel: InterestsViewModel,
    isExpandedScreen:Boolean,
    openDrawer:()->Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val tabContent = rememberTabContent(interestsViewModel)
    val (currentSection, updateSection) = rememberSaveable {
        mutableStateOf(tabContent.first().section)
    }

    InterestsScreen(
        tabContent = tabContent,
        currentSection = currentSection,
        isExpandedScreen = isExpandedScreen,
        onTabChange = updateSection,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )

}

