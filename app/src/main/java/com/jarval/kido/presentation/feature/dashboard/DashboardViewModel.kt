package com.jarval.kido.presentation.feature.dashboard

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
class DashboardViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoryUseCase,
) : ViewModel(){

    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state

    init {
        loadCategories()
    }

    fun loadCategories(){
        try {
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }

                val categories = getCategoriesUseCase()

                _state.update {
                    it.copy(
                        categories = categories,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

}