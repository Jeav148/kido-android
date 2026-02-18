package com.jarval.kido.presentation.feature.category

import androidx.compose.runtime.Stable
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem

@Stable
data class CategoryUiState (
    val isLoading: Boolean = false,
    val categories: List<CategoryItem> = emptyList(),
    val errorMessage: String? = null
)