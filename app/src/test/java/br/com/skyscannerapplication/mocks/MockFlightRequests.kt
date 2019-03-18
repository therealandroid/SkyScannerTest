package br.com.skyscannerapplication.mocks

import br.com.skyscannerapplication.model.pojo.FlightRequest

object MockFlightRequests {

    val validRequest = FlightRequest(
        cabinclass = "Economy",
        country = "UK",
        currency = "GBP",
        locale = "en-GB",
        locationschema = "iata",
        originplace = "EDI-sky",
        destinationplace = "LOND-sky ",
        outbounddate = "2019-03-18",
        inbounddate = "2019-03-19",
        adults = 1,
        children = 0,
        infants = 0,
        apikey = "prtl6749387986743898559646983194"
    )

}