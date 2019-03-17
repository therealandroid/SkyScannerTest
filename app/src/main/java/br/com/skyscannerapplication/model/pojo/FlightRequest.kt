package br.com.skyscannerapplication.model.pojo

import java.io.Serializable

/**
 * Use this object to make new flight requests
 * to the Live pricing Api
 *
 */
data class FlightRequest(
    val cabinclass: String,
    val country: String,
    val currency: String,
    val locale: String,
    val locationschema: String,
    val originplace: String,
    val destinationplace: String,
    val inbounddate: String,
    val outbounddate: String,
    val adults: Int,
    val children: Int,
    val infants: Int,
    val apikey: String
): Serializable