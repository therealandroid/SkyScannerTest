package br.com.skyscannerapplication.mocks

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult

object MockedData {
     val SAMPLE_FLIGHT_REQUEST = FlightRequest(
        cabinclass = "Economy",
        country = "UK",
        currency = "GBP",
        locale = "en-GB",
        locationschema = "iata",
        originplace = "EDI",
        destinationplace = "LHR",
        outbounddate = "2017-05-30",
        inbounddate = "2017-06-02",
        adults = 1,
        children = 0,
        infants = 0,
        apikey = "prtl6749387986743898559646983194"
    )


     val MANY_FLIGHT_RESULTS = mutableListOf(
        FlightResult(
            1
        ),
        FlightResult(
            2
        ),
        FlightResult(
            3
        ),
        FlightResult(
            4
        )
    )
}