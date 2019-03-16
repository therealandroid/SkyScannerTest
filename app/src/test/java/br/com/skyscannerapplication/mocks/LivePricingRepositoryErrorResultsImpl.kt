package br.com.skyscannerapplication.mocks

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import io.reactivex.Single

class LivePricingRepositoryErrorResultsImpl: LivePricingRepository {

    override fun searchFlights(flightRequest: FlightRequest): Single<MutableList<FlightResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}