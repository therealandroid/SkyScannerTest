package br.com.skyscannerapplication.mocks

import br.com.skyscannerapplication.model.entities.api.*
import java.text.SimpleDateFormat
import java.util.*

object MockFlights {

    /**
     * This data represents a well
     * formed Json response
     *
     */
    fun mockValidFlight(): Flight {
        var segments = mutableListOf(
            Segments(
                0,
                11235,
                11235,
                "2019-03-18T06:25:00",
                "2019-03-18T07:50:00",
                1050,
                85,
                802
            )
        )

        var carrier = mutableListOf(
            Carrier(
                1050,
                "U2",
                "easyJet",
                "https://s1.apideeplink.com/images/airlines/EZ.png",
                "EZY"
            )
        )

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var legs = mutableListOf(
            Legs(
                "11235-1903180625--32356-0-13542-1903180750",
                format.parse("2019-03-18T06:25:00"),
                format.parse("2019-03-18T07:50:00"),
                85,
                "Outbound",
                mutableListOf(
                    0
                ),
                mutableListOf(
                    1050
                )
            )
        )

        val pricingOptions = PricingOptions(345.28F)

        var itineraries = mutableListOf(
            Itineraries(
                "11235-1903180625--32356-0-13542-1903180750",
                "11235-1903180625--32356-0-13542-1903180750",
                mutableListOf(
                    pricingOptions
                )
            )
        )

        var query = Query(
            "en-GB"
        )

        return Flight(
            itineraries,
            legs,
            carrier,
            segments,
            mutableListOf(Currencies("GBP", "£")),
            mutableListOf(
                Places(
                    13554,
                    "LHR",
                    "Airport",
                    "London Heathrow"
                )
            )
        )
    }

    /**
     *
     *  0 // <- Leg ID
     */
    fun mockFlightItineraryNotContainAnValidLegId(): Flight {
        var segments = mutableListOf(
            Segments(
                0,
                11235,
                11235,
                "2019-03-18T06:25:00",
                "2019-03-18T07:50:00",
                1050,
                85,
                802
            )
        )

        var carrier = mutableListOf(
            Carrier(
                1050,
                "U2",
                "easyJet",
                "https://s1.apideeplink.com/images/airlines/EZ.png",
                "EZY"
            )
        )

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var legs = mutableListOf(
            Legs(
                "0",
                format.parse("2019-03-18T06:25:00"),
                format.parse("2019-03-18T07:50:00"),
                85,
                "Outbound",
                mutableListOf(
                    0
                ),
                mutableListOf(
                    1050
                )
            )
        )

        val pricingOptions = PricingOptions(345.28F)

        var itineraries = mutableListOf(
            Itineraries(
                "11235-1903180625--32356-0-13542-1903180750",
                "11235-1903180625--32356-0-13542-1903180750",
                mutableListOf(
                    pricingOptions
                )
            )
        )

        var query = Query(
            "en-GB"
        )

        return Flight(
            itineraries,
            legs,
            carrier,
            segments,
            mutableListOf(Currencies("GBP", "£")),
            mutableListOf(
                Places(
                    13554,
                    "LHR",
                    "Airport",
                    "London Heathrow"
                )
            )
        ,query
        )
    }

    /**
     *  1080 // <- Wrong carrier ID
     */
    fun mockFlightItineraryNotContainCarrierInfo(): Flight {
        var segments = mutableListOf(
            Segments(
                0,
                11235,
                11235,
                "2019-03-18T06:25:00",
                "2019-03-18T07:50:00",
                1050,
                85,
                802
            )
        )

        var carrier = mutableListOf(
            Carrier(
                1080, // <- Wrong carrier ID
                "U2",
                "easyJet",
                "https://s1.apideeplink.com/images/airlines/EZ.png",
                "EZY"
            )
        )

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var legs = mutableListOf(
            Legs(
                "11235-1903180625--32356-0-13542-1903180750",
                format.parse("2019-03-18T06:25:00"),
                format.parse("2019-03-18T07:50:00"),
                85,
                "Outbound",
                mutableListOf(
                    0
                ),
                mutableListOf(
                    1050
                )
            )
        )

        val pricingOptions = PricingOptions(345.28F)

        var itineraries = mutableListOf(
            Itineraries(
                "11235-1903180625--32356-0-13542-1903180750",
                "11235-19031806251903180750--32356-0-13542-0",
                mutableListOf(
                    pricingOptions
                )
            )
        )

        var query = Query(
            "en-GB"
        )

        return Flight(
            itineraries,
            legs,
            carrier,
            segments,
            mutableListOf(Currencies("GBP", "£")),
            mutableListOf(
                Places(
                    13554,
                    "LHR",
                    "Airport",
                    "London Heathrow"
                )
            )
            ,query
        )
    }

    /**
     *  -1 // <- Wrong Segment ID
     */
    fun mockFlightItineraryNotContainValidSegmentInfo(): Flight {
        var segments = mutableListOf(
            Segments(
                -1,
                11235,
                11235,
                "2019-03-18T06:25:00",
                "2019-03-18T07:50:00",
                1050,
                85,
                802
            )
        )

        var carrier = mutableListOf(
            Carrier(
                1080, // <- Wrong carrier ID
                "U2",
                "easyJet",
                "https://s1.apideeplink.com/images/airlines/EZ.png",
                "EZY"
            )
        )

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var legs = mutableListOf(
            Legs(
                "11235-1903180625--32356-0-13542-1903180750",
                format.parse("2019-03-18T06:25:00"),
                format.parse("2019-03-18T07:50:00"),
                85,
                "Outbound",
                mutableListOf(
                    0
                ),
                mutableListOf(
                    1050
                )
            )
        )

        val pricingOptions = PricingOptions(345.28F)

        var itineraries = mutableListOf(
            Itineraries(
                "11235-1903180625--32356-0-13542-1903180750",
                "11235-19031806251903180750--32356-0-13542-0",
                mutableListOf(
                    pricingOptions
                )
            )
        )

        var query = Query(
            "en-GB"
        )

        return Flight(
            itineraries,
            legs,
            carrier,
            segments,
            mutableListOf(Currencies("GBP", "£")),
            mutableListOf(
                Places(
                    13554,
                    "LHR",
                    "Airport",
                    "London Heathrow"
                )
            )
            ,query
        )
    }
}