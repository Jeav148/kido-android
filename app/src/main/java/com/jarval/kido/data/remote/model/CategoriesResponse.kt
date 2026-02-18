package com.jarval.kido.data.remote.model

data class CategoriesResponse(
    val categories: List<Category>
)

data class Category(
    val icon: Int,
    val name: String,
    val availableAmount: String
)
