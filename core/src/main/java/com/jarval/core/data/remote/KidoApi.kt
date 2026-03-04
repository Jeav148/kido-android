package com.jarval.core.data.remote

import com.jarval.core.domain.model.ProductDto
import retrofit2.http.GET

interface KidoApi {

    @GET("v1/our/api/url/product")
    suspend fun getProducts(): List<ProductDto>
}