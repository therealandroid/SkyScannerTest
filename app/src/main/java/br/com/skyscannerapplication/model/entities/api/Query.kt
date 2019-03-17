package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("Locale")
    val locale: String? = "US"
)