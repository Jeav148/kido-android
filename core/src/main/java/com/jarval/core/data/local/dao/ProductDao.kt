package com.jarval.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jarval.core.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getProducts(): Flow<List<ProductEntity>>
}