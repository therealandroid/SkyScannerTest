package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class PricingOptions(
    @SerializedName("Price")
    val price: Float = 0.0F
)