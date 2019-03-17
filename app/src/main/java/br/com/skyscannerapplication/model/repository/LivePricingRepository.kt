package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.entities.pojo.FlightRequest
import br.com.skyscannerapplication.model.entities.pojo.FlightResult
import io.reactivex.Single

interface LivePricingRepository {
    fun createSession(flightRequest: FlightRequest): Single<String>
    fun getFlights(flightRequest: FlightRequest): Single<MutableList<FlightResult>>
}