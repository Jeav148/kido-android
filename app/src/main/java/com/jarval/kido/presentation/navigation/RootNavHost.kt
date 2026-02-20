package com.jarval.kido.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jarval.kido.presentation.feature.category.CategoryScreen
import com.jarval.kido.presentation.feature.dashboard.DashboardScreen

@Composable
fun RootNavHost(
    navController: NavHostController,
    navigationDispatcher: NavigationDispatcher
){
    LaunchedEffect(Unit) {
        navigationDispatcher.navigationEvents.collect { screen ->
            navController.navigate(screen)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard
    ) {
        composable<Screen.Dashboard> {
            DashboardScreen()
        }

        composable<Screen.Category> { backStackEntry ->
            val detail : Screen.Category = backStackEntry.toRoute()
            CategoryScreen(categoryName = detail.name)
        }
    }
}