package com.jarval.kido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jarval.kido.presentation.navigation.NavigationDispatcher
import com.jarval.kido.presentation.navigation.RootNavHost
import com.jarval.kido.ui.theme.KidoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationDispatcher: NavigationDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KidoTheme {
                val navController = rememberNavController()
                Surface (modifier = Modifier.fillMaxSize()) {
                    RootNavHost(
                        navController = navController,
                        navigationDispatcher = navigationDispatcher
                    )
                }
            }
        }
    }
}

