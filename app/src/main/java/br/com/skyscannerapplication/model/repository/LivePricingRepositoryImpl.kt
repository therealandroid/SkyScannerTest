package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.apiwrapper.ApiManager
import br.com.skyscannerapplication.model.apiwrapper.ConfigutarionFile
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
                        //Make the conversion here
                        mutableListOf(
                            FlightResult(1),
                            FlightResult(2),
                            FlightResult(3),
                            FlightResult(4),
                            FlightResult(5)
                        )
                    }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}