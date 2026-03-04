package com.jarval.core.presentation.feature.product.viewmodel

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ProductUiState(
    val productSectionState: ProductSectionState = ProductSectionState.Loading,
    val categoriesSectionState: CategoriesSectionState = CategoriesSectionState.Loading
)

@Immutable
interface ProductSectionState {
    data object Loading: ProductSectionState
    data class Success(
        val productList: ImmutableList<ProductUiItem>
    ): ProductSectionState
    data class Error(val message: String): ProductSectionState
}

@Immutable
interface  CategoriesSectionState {
    data object Loading : CategoriesSectionState
    data class Error(val message: String): CategoriesSectionState
}