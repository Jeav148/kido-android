package com.jarval.kido.domain.repository

import com.jarval.kido.data.mapper.CategoryMapper
import com.jarval.kido.data.remote.KidoApi
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val kidoApi: KidoApi,
    private val categoryMapper: CategoryMapper
) : CategoryRepository {

    override suspend fun getCategoriesPreview(): List<CategoryItem> {
        delay(1000L)
        return DemoData.categories.subList(0,4)
        //return kidoApi.getCategories().map { categoryMapper.map(it) }
    }

    override suspend fun getCategories(): List<CategoryItem> {
        delay(1000L)
        return DemoData.categories
        //return kidoApi.getCategories().map { categoryMapper.map(it) }
    }
}

object DemoData{
    val categories = listOf(
        CategoryItem(1, "Baby Diapers", "12 products"),
        CategoryItem(1, "Makeup Removers", "8 products"),
        CategoryItem(1, "Period Pads", "21 products"),
        CategoryItem(1, "Lactation Pads", "6 products"),
        CategoryItem(1, "Handkerchiefs", "6 products"),
        CategoryItem(1, "Masks", "6 products"),
        CategoryItem(1, "Jair Covers", "6 products"),
    )
}