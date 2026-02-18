package com.jarval.kido.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jarval.kido.presentation.feature.category.CategoryScreen
import com.jarval.kido.presentation.feature.dashboard.DashboardScreen

@Composable
fun KidoNavigation(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ){

        composable(
            route = Routes.DASHBOARD
        ) {
            DashboardScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = Routes.CATEGORY
        ) {
            CategoryScreen(
                modifier = modifier
            )
        }

    }
}