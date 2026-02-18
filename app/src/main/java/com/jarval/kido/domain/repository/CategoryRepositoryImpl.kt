package com.jarval.kido.domain.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import com.jarval.kido.data.mapper.CategoryMapper
import com.jarval.kido.data.remote.KidoApi
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val kidoApi: KidoApi,
    private val categoryMapper: CategoryMapper
) : CategoryRepository {


    override suspend fun getCategories(): List<CategoryItem> {
        delay(1000L)
        return  listOf(
            CategoryItem(Icons.Default.Build, "Baby Diapers", "12 products"),
            CategoryItem(Icons.Default.Add, "Makeup Removers", "8 products"),
            CategoryItem(Icons.Default.Face, "Period Pads", "21 products"),
            CategoryItem(Icons.Default.ArrowForward, "Lactation Pads", "6 products")
        )
        //return kidoApi.getCategories().map { categoryMapper.map(it) }
    }
}