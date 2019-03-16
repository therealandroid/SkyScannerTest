package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

/**
 * Example:

    "OriginStation": {
    "Id": 11235,
    "ParentId": 2343,
    "Code": "EDI",
    "Type": "Airport",
    "Name": "Edinburgh"
    }

 */
data class Station(
    @SerializedName("Id")
    val id: Long,
    @SerializedName("Code")
    val code: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Name")
    val name: String
)