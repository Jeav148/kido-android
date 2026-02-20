package com.jarval.kido.domain.model.feature.dashboard

data class ProductItem(
    val id: Int,
    val title: String,
    val amount: String,
    val price: String,
    val favorite: Boolean
)
