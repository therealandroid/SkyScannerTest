package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Segments(
    @SerializedName("Id")
    val id: Long,
    @SerializedName("OriginStation")
    val originStation: Long,
    @SerializedName("DestinationStation")
    val destinationStation: Long,
    @SerializedName("DepartureDateTime")
    val departureDateTime: String,
    @SerializedName("ArrivalDateTime")
    val arrivalDateTime: String,
    @SerializedName("Carrier")
    val carrierId: Long,
    @SerializedName("Duration")
    val duration: Long,
    @SerializedName("FlightNumber")
    val flightNumber: Long
)