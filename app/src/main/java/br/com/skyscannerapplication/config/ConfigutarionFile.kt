package br.com.skyscannerapplication.config

import br.com.skyscannerapplication.model.pojo.FlightRequest

object ConfigutarionFile {

    const val LIVE_PRICING_API_URL: String = "http://partners.api.skyscanner.net/apiservices/"

    const val API_KEY: String = "ss630745725358065467897349852985"

    /**
     *  Use our 'live pricing' API to find **return flights from Edinburgh to
     *  London, departing next Monday and returning the following day**.
     *
     */
    val FLIGHT_REQUEST_FORM_DATA = FlightRequest(
        cabinclass = "Economy",
        country = "UK",
        currency = "GBP",
        locale = "en-GB",
        locationschema = "sky",
        originplace = "EDI-sky",
        destinationplace = "LOND-sky",
        outbounddate = "2019-03-18", //Go on next monday
        inbounddate = "2019-03-19", //back a day after
        adults = 1,
        children = 0,
        infants = 0,
        apikey = "ss630745725358065467897349852985"
    )

}