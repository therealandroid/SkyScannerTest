package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Segments(
    @SerializedName("Id")
    val id: Long? = 0,
    @SerializedName("OriginStation")
    val originStation: Long? = 0,
    @SerializedName("DestinationStation")
    val destinationStation: Long ? = 0,
    @SerializedName("DepartureDateTime")
    val departureDateTime: String? = "",
    @SerializedName("ArrivalDateTime")
    val arrivalDateTime: String? = "",
    @SerializedName("Carrier")
    val carrierId: Long? = 0,
    @SerializedName("Duration")
    val duration: Long? = 0,
    @SerializedName("FlightNumber")
    val flightNumber: Long? = 0
)