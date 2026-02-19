package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewModelScope
import com.jarval.kido.domain.usecase.GetCategoryPreviewUseCase
import com.jarval.kido.domain.usecase.GetPopularProductUseCase
import com.jarval.kido.presentation.feature.MviViewModel
import com.jarval.kido.presentation.feature.dashboard.DashboardViewModel.Constants.POPULAR_PRODUCT_LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoryPreviewUseCase,
    private val getPopularProductUseCase: GetPopularProductUseCase
) : MviViewModel<DashboardUiIntent, DashboardUiState, DashboardUiEffect>(
    DashboardUiState()
) {
    object Constants {
        const val POPULAR_PRODUCT_LIMIT = 5
    }

    init {
        loadCategories()
        loadPopularProducts(POPULAR_PRODUCT_LIMIT)
    }

    fun loadCategories() {
        try {
            viewModelScope.launch {
                reduce {
                    copy(
                        isLoading = true
                    )
                }
                val categories = getCategoriesUseCase()
                reduce {
                    copy(
                        categories = categories.toMutableList(),
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            reduce {
                copy(
                    isLoading = false,
                    hasError = true,
                    errorMessage = e.message
                )
            }
        }
    }

    fun loadPopularProducts(limit: Int) {
        try {
            reduce {
                copy(
                    popularProducts = mutableStateListOf(),
                    isLoading = true
                )
            }
            viewModelScope.launch {
                val popularProducts = getPopularProductUseCase(limit)
                reduce {
                    copy(
                        popularProducts = popularProducts.toMutableStateList(),
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            reduce {
                copy(
                    isLoading = false,
                    hasError = true,
                    errorMessage = e.message
                )
            }
        }
    }

    private fun openCategories() {
        viewModelScope.launch {
            emitEffect {
                DashboardUiEffect.NavigateToCategory
            }
        }
    }

    override fun handleIntent(intent: DashboardUiIntent) {
        when (intent) {
            DashboardUiIntent.OpenCategories -> openCategories()
            else -> {}
        }
    }

}