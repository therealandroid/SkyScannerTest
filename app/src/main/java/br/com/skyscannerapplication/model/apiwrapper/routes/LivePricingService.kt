package br.com.skyscannerapplication.model.apiwrapper.routes

import br.com.skyscannerapplication.model.entities.api.Flights
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface LivePricingService {

    /**
     * As mentioned in the documentation:  "We do not support client-side implementation of our
     * Live Prices API using CORS requests due to the potential security
     * risk of exposing account API keys."
     *
    curl "http://partners.api.skyscanner.net/apiservices/pricing/v1.0"
    -X POST
    -H "Content-Type: application/x-www-form-urlencoded"
    -d 'cabinclass=Economy
    &country=UK
    &currency=GBP
    &locale=en-GB
    &locationSchema=iata
    &originplace=EDI
    &destinationplace=LHR
    &outbounddate=2017-05-30
    &inbounddate=2017-06-02
    &adults=1
    &children=0
    &infants=0
    &apikey= <YourApiKeyHere>'
     **/
    @FormUrlEncoded
    @POST("pricing/v1.0")
    fun createSession(
        @Field("country") country: String,
        @Field("currency") currency: String,
        @Field("locale") locale: String,
        @Field("originPlace") originPlace: String,
        @Field("destinationPlace") destinationPlace: String,
        @Field("outboundDate") outboundDate: String,
        @Field("inboundDate") inboundDate: String,
        @Field("cabinClass") cabinClass: String,
        @Field("adults") adults: Int,
        @Field("children") children: Int,
        @Field("infants") infants: Int,
        @Field("apiKey") apiKey: String
    ): Single<Response<Any>>


    /**
     * Polling the results
     * We will get the flight results here
     * Url standard: http://partners.api.skyscanner.net/apiservices/pricing/uk1/v1.0/{SessionKey}?apiKey={apiKey}
     * We will also append the api key that is provided by the client
     *
     */
    @GET
    fun getFlights(@Url string: String, @Query("apiKey") apiKey: String): Single<List<Flights>>

}