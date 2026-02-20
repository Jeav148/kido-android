package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewModelScope
import com.jarval.kido.domain.usecase.GetCategoryPreviewUseCase
import com.jarval.kido.domain.usecase.GetPopularProductUseCase
import com.jarval.kido.presentation.feature.MviViewModel
import com.jarval.kido.presentation.feature.dashboard.DashboardViewModel.Constants.POPULAR_PRODUCT_LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
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
        handleIntent(DashboardUiIntent.LoadAllData)
    }

    override fun handleIntent(intent: DashboardUiIntent) {
        when (intent) {
            DashboardUiIntent.LoadAllData -> {
                viewModelScope.launch {
                    launch {
                        loadCategories()
                    }
                    launch {
                        loadPopularProducts(POPULAR_PRODUCT_LIMIT)
                    }
                }
            }

            DashboardUiIntent.OpenCategories -> openCategories()
            else -> {}
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            try {
                reduce {
                    copy(categoryState = CategoryState.Loading)
                }
                val categories = getCategoriesUseCase()
                reduce {
                    copy(
                        categoryState = CategoryState.Success(
                            categories.toImmutableList()
                        )
                    )
                }
            } catch (e: Exception) {
                reduce {
                    copy(
                        categoryState = CategoryState.Error(
                            e.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }

    }

    fun loadPopularProducts(limit: Int) {
        try {
            reduce {
                copy(
                    productState = ProductState.Loading
                )
            }
            viewModelScope.launch {
                val popularProducts = getPopularProductUseCase(limit)
                reduce {
                    copy(
                        productState = ProductState.Success(
                            products = popularProducts.toImmutableList()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            reduce {
                copy(
                    categoryState = CategoryState.Error(
                        e.message ?: "Something went wrong"
                    )
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

}