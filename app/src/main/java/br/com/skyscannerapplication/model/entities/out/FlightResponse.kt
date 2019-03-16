package br.com.skyscannerapplication.model.entities.out

import java.util.*

/**
 * The object that represents the view.
 *
 */
//data class FlightResponse(
//    val id: String? = "",
//    val departure: Date? = Date(),
//    val arrival: Date? = Date(),
//    val duration: Long? = 0,
//    val directionality: String? = "",
//    val carrierName: String? = "",
//    val carrierImageUrl: String? = "",
//    val carrierDisplayCode: String? = ""
//)

data class FlightResponse(
    val id: String? = "",
    val departure: Date? = Date(),
    val arrival: Date? = Date(),
    val duration: Long? = 0,
    val directionality: String? = "",
    val carrierName: String? = "",
    val carrierImageUrl: String? = "",
    val carrierDisplayCode: String? = ""
)