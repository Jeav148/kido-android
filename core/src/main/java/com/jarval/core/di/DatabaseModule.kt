package com.jarval.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jarval.core.data.local.KidoDatabase
import com.jarval.core.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): KidoDatabase {
        return Room.databaseBuilder(
            context,
            KidoDatabase::class.java,
            "kido.db")
            .build()
    }

    @Provides
    fun provideProductDao(db: KidoDatabase): ProductDao{
        return db.productDao()
    }


}