package br.com.skyscannerapplication.model.repository

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LivePricingRepositoryImpl {

    fun searchFlights(flightRequest: FlightRequest): Single<MutableList<FlightResult>> {
        //TODO remove the empty result after integration
        return Single.just(mutableListOf<FlightResult>())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
    }
}