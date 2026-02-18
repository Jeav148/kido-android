package com.jarval.kido.presentation.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarval.kido.domain.usecase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryUiState())
    val state: StateFlow<CategoryUiState> = _state

    init {
        loadCategories()
    }

    fun loadCategories(){
        try {
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isLoading = true,
                        categories = emptyList(),
                        errorMessage = null
                    )
                }
                val categories = getCategoriesUseCase()
                _state.update {
                    it.copy(
                        isLoading = false,
                        categories = categories,
                        errorMessage = null
                    )
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    categories = emptyList(),
                    errorMessage = e.message
                )
            }
        }
    }
}
