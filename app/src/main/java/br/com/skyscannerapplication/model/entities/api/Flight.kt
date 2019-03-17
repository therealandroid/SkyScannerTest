package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

/**
 * The root object
 *
 */
data class Flight(

    @SerializedName("Itineraries")
    val itineraries: List<Itineraries> = mutableListOf(),
    @SerializedName("Legs")
    val legs: List<Legs> = mutableListOf(),
    @SerializedName("Carriers")
    val carriers: List<Carrier> = mutableListOf(),
    @SerializedName("Segments")
    val segments: List<Segments> = mutableListOf(),
    @SerializedName("Currencies")
    val currencies: List<Currencies> = mutableListOf(),
    @SerializedName("Places")
    val places: List<Places> = mutableListOf()


)