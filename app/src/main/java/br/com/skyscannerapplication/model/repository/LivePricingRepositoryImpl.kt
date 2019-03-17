package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.apiwrapper.ApiManager
import br.com.skyscannerapplication.model.apiwrapper.ConfigutarionFile
import br.com.skyscannerapplication.model.entities.api.Flight
import br.com.skyscannerapplication.model.entities.out.FlightInfo
import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult
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
        var itineraries = flight.itineraries
        var legs = flight.legs
        var segments = flight.segments
        var carriers = flight.carriers

        return itineraries.map { itinerary ->
            //Find itinerary Inbound and Outbound leg

            val outboundLeg = legs.find { legs ->
                legs.id == itinerary.outBoundLegId
            }
            val inboudLeg = legs.find { legs ->
                legs.id == itinerary.inboundLegId
            }

            //////////////////FIND CARRIERS////////
            //find leg carriers
            //First find the leg Ids from the legs array
            val outboundCarrierId = outboundLeg?.carrierIds?.first()
            val inboudCarrierId = inboudLeg?.carrierIds?.first()

            var outBoundCarrier = carriers.find { carrier ->
                outboundCarrierId?.equals(carrier.id)!!
            }
            var inboudCarrier = carriers.find { carrier ->
                inboudCarrierId?.equals(carrier.id)!! //TODO fix the nullpointers
            }

            //////////////////FIND SEGMENTS//////////////
            //find the leg segments
            //First find the Segment Ids from legs array

            var outBoundSegmentId = outboundLeg?.segmentIds?.first()
            var inBoundSegmentId = inboudLeg?.segmentIds?.first()

            var outBoundSegment = segments.find {
                it.id == outBoundSegmentId
            }

            var inBoundSegment = segments.find {
                it.id == inBoundSegmentId
            }

            val outFlightResult = FlightInfo(
                departure = outboundLeg?.departure,
                arrival = outboundLeg?.arrival,
                duration = outboundLeg?.duration,
                carrierDisplayCode = outBoundCarrier?.displayCode,
                carrierImageUrl = outBoundCarrier?.imageUrl,
                carrierName = outBoundCarrier?.name,
                price = itinerary.pricingOptions.first().price
            )

            val arriveFlightResult = FlightInfo(
                departure = inboudLeg?.departure,
                arrival = inboudLeg?.arrival,
                duration = inboudLeg?.duration,
                carrierDisplayCode = inboudCarrier?.displayCode,
                carrierImageUrl = inboudCarrier?.imageUrl,
                carrierName = inboudCarrier?.name,
                price = itinerary.pricingOptions.first().price
            )

            FlightResult(arriveFlightResult, outFlightResult)
        }.toMutableList()
    }


}