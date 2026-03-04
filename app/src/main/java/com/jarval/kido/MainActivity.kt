package com.jarval.kido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.jarval.kido.presentation.navigation.NavigationDispatcher
import com.jarval.kido.presentation.navigation.RootNavHost
import com.jarval.kido.ui.theme.KidoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationDispatcher : NavigationDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KidoTheme {
                val navController = rememberNavController()
                Scaffold() { padding ->
                    RootNavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        navigationDispatcher = navigationDispatcher
                    )
                }
            }
        }
    }
}

