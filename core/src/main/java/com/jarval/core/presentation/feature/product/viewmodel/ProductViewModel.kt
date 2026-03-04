package com.jarval.core.presentation.feature.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarval.core.domain.usecase.GetProductUseCase
import com.jarval.core.domain.usecase.GetProductsFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductsFromDBUseCase: GetProductsFromDBUseCase
): ViewModel() {

    private var _state = MutableStateFlow(ProductUiState())
    val state = _state.asStateFlow()

    private var _effect = MutableSharedFlow<ProductUiEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: ProductUiIntent){
        when(intent){
            is ProductUiIntent.LoadProducts -> {
                getProducts()
            }
            is ProductUiIntent.OpenProduct -> {

            }
        }
    }

    init {
        handleIntent(ProductUiIntent.LoadProducts)
    }

    private fun getProducts(){
        viewModelScope.launch {
            //Setup loading
            _state.update {
                it.copy(
                    productSectionState = ProductSectionState.Loading
                )
            }
            try {
                val products = getProductUseCase()
                _state.update {
                    it.copy(
                        productSectionState = ProductSectionState.Success(products.toImmutableList())
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        productSectionState = ProductSectionState.Error(e.message ?: "Something went wrong")
                    )
                }
            }
        }
    }

    private fun getCachedProducts(){
        viewModelScope.launch {
            getProductsFromDBUseCase()
                .conflate()
                .collect { products ->
                try {
                    _state.update {
                        it.copy(
                            productSectionState = ProductSectionState.Success(products.toImmutableList())
                        )
                    }
                } catch (e: Exception) {
                    _state.update {
                        it.copy(
                            productSectionState = ProductSectionState.Error(e.message ?: "Something went wrong")
                        )
                    }
                }
            }
        }
    }

}