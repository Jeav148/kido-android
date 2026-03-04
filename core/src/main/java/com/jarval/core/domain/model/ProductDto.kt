package com.jarval.core.domain.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("description") val description: String
)