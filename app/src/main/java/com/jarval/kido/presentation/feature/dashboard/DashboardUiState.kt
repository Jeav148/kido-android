package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import kotlinx.collections.immutable.ImmutableList

@Stable
data class DashboardUiState(
    val categoryState: CategoryState = CategoryState.Loading,
    val productState: ProductState = ProductState.Loading
)


@Immutable
sealed interface CategoryState {
    data object Loading: CategoryState
    data class Success(val categories: ImmutableList<CategoryItem>) : CategoryState
    data class Error(val message: String): CategoryState
}

@Immutable
sealed interface ProductState {
    data object Loading: ProductState
    data class Success(val products: ImmutableList<ProductItem>): ProductState
    data class Error(val message: String): ProductState
}
