package com.jarval.kido.data.repository

import com.jarval.kido.data.mapper.ProductMapper
import com.jarval.kido.data.remote.KidoApi
import com.jarval.kido.data.remote.model.ProductResponse
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val kidoApi: KidoApi,
    private val popularProductMapper: ProductMapper
) : ProductRepository {

    override suspend fun getPopularProducts(limit: Int): List<ProductItem> {
        /*return kidoApi.getPopularProducts(limit = limit).map {
            popularProductMapper.mapper(it)
        }*/
        return DemoProducts.products.subList(0, limit).map {
            popularProductMapper.mapper(it)
        }
    }
}

object DemoProducts{

    val products = listOf(
        ProductResponse("Diapers", "10", "$12.50"),
        ProductResponse("Diapers", "9", "$12.50"),
        ProductResponse("Diapers", "8", "$12.50"),
        ProductResponse("Diapers", "7", "$12.50"),
        ProductResponse("Diapers", "6", "$12.50"),
        ProductResponse("Diapers", "5", "$12.50")
    )

}