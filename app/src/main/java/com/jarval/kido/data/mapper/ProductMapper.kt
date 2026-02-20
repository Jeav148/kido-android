package com.jarval.kido.data.mapper

import com.jarval.kido.data.remote.model.ProductResponse
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapper(product: ProductResponse): ProductItem {
        return ProductItem(
            id = product.id,
            title = product.name,
            amount = product.inventoryAmount,
            price = product.price,
            favorite = product.favorite
        )
    }
}