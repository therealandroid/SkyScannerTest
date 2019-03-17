package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

/**
 * The root object
 *
 */
data class Flight(

    @SerializedName("Itineraries")
    val itineraries: List<Itineraries>,
    @SerializedName("Legs")
    val legs: List<Legs>,
    @SerializedName("Carriers")
    val carriers: List<Carrier>,
    @SerializedName("Segments")
    val segments: List<Segments>,
    @SerializedName("Currencies")
    val currencies: List<Currencies>


)