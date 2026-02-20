package com.jarval.kido.data.mapper

import com.jarval.kido.data.remote.model.CategoryResponse
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun map(data: CategoryResponse): CategoryItem {
        return CategoryItem(
            icon = data.icon,
            title = data.name,
            subtitle = data.availableAmount
        )
    }

}
