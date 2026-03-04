package com.jarval.core.domain.usecase

import com.jarval.core.data.repository.ProductRepository
import com.jarval.core.presentation.feature.product.viewmodel.ProductUiItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsFromDBUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(): Flow<List<ProductUiItem>>{
        return productRepository.getCacheProducts()
    }
}