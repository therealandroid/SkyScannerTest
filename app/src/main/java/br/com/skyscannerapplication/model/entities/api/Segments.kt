package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Segments(
    @SerializedName("Id")
    val id: Long,
    @SerializedName("OriginStation")
    val OriginStation: Long,
    @SerializedName("DestinationStation")
    val DestinationStation: Long,
    @SerializedName("DepartureDateTime")
    val DepartureDateTime: String,
    @SerializedName("ArrivalDateTime")
    val ArrivalDateTime: String,
    @SerializedName("Carrier")
    val carrierId: Long,
    @SerializedName("Duration")
    val duration: Long,
    @SerializedName("FlightNumber")
    val flightNumber: Long
)