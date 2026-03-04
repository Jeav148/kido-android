package com.jarval.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable data class Product(val name: String) : Screen

}