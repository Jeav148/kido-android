package com.jarval.kido.domain.usecase

import com.jarval.kido.data.repository.ProductRepository
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import javax.inject.Inject

class GetPopularProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(limit: Int): List<ProductItem> {
        return productRepository.getPopularProducts(limit)
    }

}
