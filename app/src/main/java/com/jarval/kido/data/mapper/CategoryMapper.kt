package com.jarval.kido.data.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import com.jarval.kido.data.remote.model.Category
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun map(data: Category): CategoryItem {
        return CategoryItem(
            icon = data.icon,
            title = data.name,
            subtitle = data.availableAmount
        )
    }

}
