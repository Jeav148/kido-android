package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.ProductItem

@Stable
data class DashboardUiState(
    val isLoading: Boolean = false,
    val categories: MutableList<CategoryItem> = mutableStateListOf(),
    val popularProducts: MutableList<ProductItem> = mutableStateListOf(),
    val hasError: Boolean = false,
    val errorMessage: String? = null
)
