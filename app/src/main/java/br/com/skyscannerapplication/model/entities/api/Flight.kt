package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("Legs")
    val legs: List<Legs>
)