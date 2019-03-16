package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Carrier(
    @SerializedName("Id")
    val id: Long,
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("DisplayCode")
    val displayCode: String
)