package com.jarval.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jarval.core.data.local.dao.ProductDao
import com.jarval.core.data.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    version = 1
)
abstract class KidoDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}