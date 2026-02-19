package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.runtime.Stable
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.ProductItem

@Stable
data class DashboardUiState(
    val isLoading: Boolean = false,
    val categories: List<CategoryItem> = emptyList(),
    val popularProducts: List<ProductItem> = emptyList(),
    val hasError: Boolean = false,
    val errorMessage: String? = null
)
