package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.apiwrapper.ApiManager
import br.com.skyscannerapplication.model.apiwrapper.ConfigutarionFile
import br.com.skyscannerapplication.model.entities.api.Flight
import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResponse
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
    override fun getFlights(flightRequest: FlightRequest): Single<MutableList<FlightResponse>> {
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
     * We process the data to be manipulated by the view later
     *
     */
    private fun processFlightResult(flight: Flight): MutableList<FlightResponse> {
        //TODO add the necessary data
        return flight.legs.map { leg ->
            FlightResponse(
                id = leg.id,
                departure = leg.departure,
                arrival = leg.arrival,
                duration = leg.duration,
                directionality = leg.directionality
            )
        }.toMutableList()
    }
}