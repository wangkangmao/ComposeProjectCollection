package com.wangkm.owl.ui.courses

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.wangkm.owl.R
import com.wangkm.owl.model.courses
import com.wangkm.owl.model.topics
import com.wangkm.owl.ui.MainDestinations


/**
 * @author: created by wangkm
 * @time: 2022/07/27 12:18
 * @desc：
 * @email: 1240413544@qq.com
 */

fun NavGraphBuilder.courses(
    onCourseSelected: (Long, NavBackStackEntry) -> Unit,
    onboardingComplete: State<Boolean>, // https://issuetracker.google.com/174783110
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(CourseTabs.FEATURED.route) { from ->
        // Show onboarding instead if not shown yet.
        LaunchedEffect(onboardingComplete) {
            if (!onboardingComplete.value) {
                navController.navigate(MainDestinations.ONBOARDING_ROUTE)
            }
        }
        if (onboardingComplete.value) { // Avoid glitch when showing onboarding
            FeaturedCourses(
                courses = courses,
                selectCourse = { id -> onCourseSelected(id, from) },
                modifier = modifier
            )
        }
    }
    composable(CourseTabs.MY_COURSES.route) { from ->
        MyCourses(
            courses = courses,
            { id -> onCourseSelected(id, from) },
            modifier
        )
    }
    composable(CourseTabs.SEARCH.route) {
        SearchCourses(topics, modifier)
    }
}

@Composable
fun CoursesAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_lockup_white),
            contentDescription = null
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.label_profile)
            )
        }
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    MY_COURSES(R.string.my_courses, R.drawable.ic_grain, CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured, R.drawable.ic_featured, CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_search, CoursesDestinations.SEARCH_COURSES_ROUTE)
}

/**
 * Destinations used in the ([OwlApp]).
 */
private object CoursesDestinations {
    const val FEATURED_ROUTE = "courses/featured"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "courses/search"
}
