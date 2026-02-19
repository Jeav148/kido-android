package com.jarval.kido.data.repository

import com.jarval.kido.domain.model.feature.dashboard.ProductItem

interface ProductRepository {

    suspend fun getPopularProducts(limit: Int): List<ProductItem>

}
