package com.jarval.kido.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("name") val name: String,
    @SerializedName("inventory_amount") val inventoryAmount: String,
    @SerializedName("price") val price: String
)
