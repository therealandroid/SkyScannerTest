package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * InboundLegId and OutboundLegId are specializations
 * of Legs
 *
 * The purpose of using @SerializedName is to keep using kotlin variable patterns as camel case
 *
 */
data class Legs(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Departure")
    val departure: Date,
    @SerializedName("Arrival")
    val arrival: Date,
    @SerializedName("Duration")
    val duration: Long,
    @SerializedName("Directionality")
    val directionality: String
)
