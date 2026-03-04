package com.jarval.core.data.repository

import com.jarval.core.presentation.feature.product.viewmodel.ProductUiItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): List<ProductUiItem>

    suspend fun getCacheProducts(): Flow<List<ProductUiItem>>

}