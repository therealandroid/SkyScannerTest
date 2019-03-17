package br.com.skyscannerapplication.model.entities.out

import java.util.*

data class FlightInfo(
    val departure: Date? = Date(),
    val arrival: Date? = Date(),
    val duration: Long? = 0,
    val carrierName: String? = "",
    val carrierImageUrl: String? = "",
    val carrierDisplayCode: String? = "",
    val direction: String? = "",
    val price: Float? = 0.0f,
    val originAirport: String? = "",
    val destinyAirport: String? = ""
)

/**
 * This POJO class
 */
data class FlightResult(
    val arriveFlightInfo: FlightInfo,
    val outFlightInfo: FlightInfo,
    val currencySymbol: String = ""
)