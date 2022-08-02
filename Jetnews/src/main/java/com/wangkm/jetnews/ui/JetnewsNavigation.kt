package com.wangkm.jetnews.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * @author: created by wangkm
 * @time: 2022/06/28 13:20
 * @desc：
 * @email: 1240413544@qq.com
 */


/**
 * Destinations used in the [JetnewsApp].
 */
object JetnewsDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"
}

/**
 * Models the navigation actions in the app.
 */
class JetnewsNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(JetnewsDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToInterests: () -> Unit = {
        navController.navigate(JetnewsDestinations.INTERESTS_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

