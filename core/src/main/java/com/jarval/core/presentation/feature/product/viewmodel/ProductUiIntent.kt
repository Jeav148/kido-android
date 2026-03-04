package com.jarval.core.presentation.feature.product.viewmodel

sealed class ProductUiIntent {
    data object LoadProducts : ProductUiIntent()
    data class OpenProduct(val productId: Int): ProductUiIntent()
}
