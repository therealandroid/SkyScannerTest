package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("Code")
    var code: String,
    @SerializedName("Symbol")
    var symbol: String
)