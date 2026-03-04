package com.jarval.core.data.repository

import com.jarval.core.data.local.dao.ProductDao
import com.jarval.core.data.mapper.ProductMapper
import com.jarval.core.data.remote.KidoApi
import com.jarval.core.presentation.feature.product.viewmodel.ProductUiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val kidoApi: KidoApi,
    val productMapper: ProductMapper,
    val dao: ProductDao
) : ProductRepository {

    override suspend fun getProducts(): List<ProductUiItem> {
        return kidoApi.getProducts().map { prod ->
            productMapper.map(prod)
        }
    }

    override suspend fun getCacheProducts(): Flow<List<ProductUiItem>> {
        return dao.getProducts().map { entities ->
            entities.map {
                productMapper.map(it)
            }
        }
    }
}