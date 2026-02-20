package com.jarval.kido.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable data object Dashboard: Screen
    @Serializable data class Category(val name: String): Screen
}