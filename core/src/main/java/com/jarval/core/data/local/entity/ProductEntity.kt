package com.jarval.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products")
data class ProductEntity(
    @PrimaryKey val productId: String,
    val name: String
)
