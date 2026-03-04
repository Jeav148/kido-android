package com.jarval.core.domain.usecase

import com.jarval.core.data.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = productRepository.getProducts()
}
