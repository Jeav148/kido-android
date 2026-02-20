package com.jarval.kido.data.remote

import com.jarval.kido.data.remote.model.CategoryResponse
import com.jarval.kido.data.remote.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KidoApi {

    @GET("/api/categories")
    suspend fun getCategories(): List<CategoryResponse>

    @GET("v1/products/popular/{limit}")
    suspend fun getPopularProducts(@Path("limit") limit: Int): List<ProductResponse>


}