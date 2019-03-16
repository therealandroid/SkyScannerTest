package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface LivePricingRepository {

    fun searchFlights(flightRequest: FlightRequest): Single<MutableList<FlightResult>>
}