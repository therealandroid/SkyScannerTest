package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * InboundLegId and OutboundLegId are specializations
 * of LegId
 *
 * The purpose of using @SerializedName is to keep using kotlin variable patterns as camel case
 *
 */
data class LegId(
    @SerializedName("OriginStation")
    val originStation: Station,
    @SerializedName("DestinationStation")
    val destinationStation: Station,
    @SerializedName("Departure")
    val departure: Date,
    @SerializedName("Arrival")
    val arrival: Date,
    @SerializedName("Duration")
    val duration: Long,
    @SerializedName("Carriers")
    val journeyMode: List<Carrier> = mutableListOf()
)
