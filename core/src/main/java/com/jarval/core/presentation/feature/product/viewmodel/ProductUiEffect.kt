package com.jarval.core.presentation.feature.product.viewmodel

sealed class ProductUiEffect {

    data class LaunchToast(val message: String): ProductUiEffect()
    data class NavigateToProductDetail(val productId: Int): ProductUiEffect()

}
