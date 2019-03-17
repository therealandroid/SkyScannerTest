package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.apiwrapper.ApiManager
import br.com.skyscannerapplication.config.ConfigutarionFile
import br.com.skyscannerapplication.model.entities.api.Flight
import br.com.skyscannerapplication.model.pojo.FlightInfo
import br.com.skyscannerapplication.model.pojo.FlightRequest
import br.com.skyscannerapplication.model.pojo.FlightResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LivePricingRepositoryImpl : LivePricingRepository {


    override fun createSession(flightRequest: FlightRequest): Single<String> {
        return ApiManager.livePricingRoute.createSession(
            flightRequest.country,
            flightRequest.currency,
            flightRequest.locale,
            flightRequest.originplace,
            flightRequest.destinationplace,
            flightRequest.outbounddate,
            flightRequest.inbounddate,
            flightRequest.cabinclass,
            flightRequest.adults,
            flightRequest.children,
            flightRequest.infants,
            flightRequest.apikey
        ).map {
            it.headers().get("Location")
        }
    }

    /**
     * This method make two calls:
     *
     * first it will create the session and get the redirect url to make the second call
     * After get the url we will also append the Api Key Url provided by the client and finally
     * we will retrieve the flight.
     *
     *  Following the Repository pattern, we will also give to the caller the appropriated view objects, so it will not
     *  need to handle conversions, mapping etc.
     *
     */
    override fun getFlights(flightRequest: FlightRequest): Single<MutableList<FlightResult>> {
        return createSession(flightRequest)
            .flatMap { redirectUrl ->
                ApiManager.livePricingRoute
                    .getFlights(redirectUrl, ConfigutarionFile.API_KEY)
                    .map {
                        processFlightResult(it)
                    }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * We process the data so we can just get what we want to display
     * in our screen
     *
     *   Let's get back to the exercise:
     *
     *   From *Itineraries* We just need it to get other values to use like show in the mockup design
     *
     *   From **Legs** we just need the **duration**, *departure* and *arrival* time

     *   From **Segments** - Individual flight information with directionality.
     *
     */

    private fun processFlightResult(flight: Flight): MutableList<FlightResult> {
        val itineraries = flight.itineraries

        return itineraries.map { itinerary ->
            val legs = flight.legs

            val outboundLeg = legs.find {
                it.id == itinerary.outBoundLegId
            }

            val inboudLeg = legs.find {
                it.id == itinerary.inboundLegId
            }


            val carriers = flight.carriers

            val outboundCarrierId = outboundLeg?.carrierIds?.first()
            val inboudCarrierId = inboudLeg?.carrierIds?.first()

            var outBoundCarrier = carriers.find { carrier ->
                outboundCarrierId?.equals(carrier.id)!!
            }

            var inboudCarrier = carriers.find { carrier ->
                inboudCarrierId?.equals(carrier.id)!! //TODO fix the nullpointers
            }


            val segments = flight.segments

            var outBoundSegmentId = outboundLeg?.segmentIds?.first()
            var inBoundSegmentId = inboudLeg?.segmentIds?.first()

            var outBoundSegment = segments.find {
                it.id == outBoundSegmentId
            }

            var inBoundSegment = segments.find {
                it.id == inBoundSegmentId
            }


            val places = flight.places

            val outBoundOriginPlace = places.find {
                outBoundSegment?.destinationStation == it.id
            }

            val outBoundDestinyPlace = places.find {
                outBoundSegment?.originStation == it.id
            }

            val inBoundOriginPlace = places.find {
                inBoundSegment?.originStation == it.id
            }

            val inBoundDestinyPlace = places.find {
                inBoundSegment?.destinationStation == it.id
            }

            val outboundFlightResult = FlightInfo(
                departure = outboundLeg?.departure,
                arrival = outboundLeg?.arrival,
                duration = outboundLeg?.duration,
                carrierDisplayCode = outBoundCarrier?.displayCode,
                carrierImageUrl = outBoundCarrier?.imageUrl,
                carrierName = outBoundCarrier?.name,
                price = itinerary.pricingOptions.first().price,
                direction = outboundLeg?.directionality,
                originAirport = outBoundOriginPlace?.code,
                destinyAirport = outBoundDestinyPlace?.code
            )
            val inboundFlightResult = FlightInfo(
                departure = inboudLeg?.departure,
                arrival = inboudLeg?.arrival,
                duration = inboudLeg?.duration,
                carrierDisplayCode = inboudCarrier?.displayCode,
                carrierImageUrl = inboudCarrier?.imageUrl,
                carrierName = inboudCarrier?.name,
                price = itinerary.pricingOptions.first().price,
                direction = inboudLeg?.directionality,
                originAirport = inBoundOriginPlace?.code,
                destinyAirport = inBoundDestinyPlace?.code
            )

            FlightResult(
                inboundFlightResult,
                outboundFlightResult,
                flight.currencies.first().symbol,
                flight.query.locale
            )
        }.toMutableList()
    }


}