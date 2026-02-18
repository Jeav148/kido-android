package com.jarval.kido.domain.repository

import com.jarval.kido.domain.model.feature.dashboard.CategoryItem

interface CategoryRepository  {

    suspend fun getCategoriesPreview(): List<CategoryItem>

    suspend fun getCategories(): List<CategoryItem>

}
