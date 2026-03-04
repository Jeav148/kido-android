package com.jarval.core.data.mapper

import com.jarval.core.data.local.entity.ProductEntity
import com.jarval.core.domain.model.ProductDto
import com.jarval.core.presentation.feature.product.viewmodel.ProductUiItem
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun map(productDto: ProductDto): ProductUiItem{
        return ProductUiItem(
            id = productDto.id,
            name = productDto.title,
            description = productDto.description
        )
    }

    fun map(productEntity: ProductEntity): ProductUiItem {
        return ProductUiItem(
            id = productEntity.productId.toInt(),
            name = productEntity.name,
            description = ""
        )
    }
}
