package com.jarval.core.presentation.feature.product.viewmodel

import androidx.compose.runtime.Stable

@Stable
data class ProductUiItem(
    val id: Int,
    val name: String,
    val description: String
)
