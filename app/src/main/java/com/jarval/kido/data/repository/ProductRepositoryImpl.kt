package com.jarval.kido.data.repository

import com.jarval.kido.data.mapper.ProductMapper
import com.jarval.kido.data.remote.KidoApi
import com.jarval.kido.data.remote.model.ProductResponse
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val kidoApi: KidoApi,
    private val popularProductMapper: ProductMapper
) : ProductRepository {

    override suspend fun getPopularProducts(limit: Int): List<ProductItem> {
        /*return kidoApi.getPopularProducts(limit = limit).map {
            popularProductMapper.mapper(it)
        }*/
        delay(4000L)
        return DemoProducts.products.subList(0, limit).map {
            popularProductMapper.mapper(it)
        }
    }
}

object DemoProducts {

    val products = listOf(
        ProductResponse(
            id = 1,
            name = "Diapers",
            inventoryAmount = "10",
            price = "$12.50",
            favorite = true
        ),
        ProductResponse(
            id = 2,
            name = "Diapers",
            inventoryAmount = "9",
            price = "$12.50",
            favorite = false
        ),
        ProductResponse(
            id = 3,
            name = "Diapers",
            inventoryAmount = "8",
            price = "$12.50",
            favorite = true
        ),
        ProductResponse(
            id = 4,
            name = "Diapers",
            inventoryAmount = "7",
            price = "$12.50",
            favorite = false
        ),
        ProductResponse(
            id = 5,
            name = "Diapers",
            inventoryAmount = "6",
            price = "$12.50",
            favorite = true
        ),
        ProductResponse(
            id = 6,
            name = "Diapers",
            inventoryAmount = "5",
            price = "$12.50",
            favorite = false
        )
    )

}