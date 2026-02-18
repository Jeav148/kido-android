package com.jarval.kido.presentation.feature.dashboard

import com.jarval.kido.domain.model.feature.dashboard.CategoryItem

data class DashboardUiState(
    val isLoading: Boolean = false,
    val categories: List<CategoryItem> = emptyList(),
    val errorMessage: String? = null
)
