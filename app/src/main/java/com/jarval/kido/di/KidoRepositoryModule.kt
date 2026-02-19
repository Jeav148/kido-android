package com.jarval.kido.di

import com.jarval.kido.data.repository.ProductRepository
import com.jarval.kido.data.repository.ProductRepositoryImpl
import com.jarval.kido.domain.repository.CategoryRepository
import com.jarval.kido.domain.repository.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class KidoRepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun bindPopularProductRepository(impl: ProductRepositoryImpl): ProductRepository

}