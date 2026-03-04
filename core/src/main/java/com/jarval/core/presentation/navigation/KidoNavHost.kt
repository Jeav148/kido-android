package com.jarval.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jarval.core.presentation.feature.product.ui.ProductScreen

@Composable
fun KidoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationDispatcher: NavigationDispatcher
){

    LaunchedEffect(Unit) {
        navigationDispatcher.navEvents.collect {screen ->
            navController.navigate(screen)
        }
    }

    NavHost (
        navController = navController,
        startDestination = Screen.Product("")
    ){
        composable<Screen.Product> { entry ->
            val detail = entry.toRoute<Screen.Product>()
            val name = detail.name
            ProductScreen()
        }
    }

}