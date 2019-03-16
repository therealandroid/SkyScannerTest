package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import br.com.skyscannerapplication.view.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable

class SearchFlightsPresenter(
    private val repository: LivePricingRepository,
    private val view: SearchFlightsContract.View
) :
    BasePresenter {

    private val compositeDisposable = CompositeDisposable()

    /**
     * This function make a call to living price Api
     * returning all flights available for the matching input
     *
     */
    fun searchFlights(flightRequest: FlightRequest) {
        view.setProgressIndicator(true)

        compositeDisposable.add(repository.searchFlights(flightRequest)
            .doFinally {
                view.setProgressIndicator(false)
            }
            .subscribe({
                if (it.isNotEmpty()) {
                    view.showFlightResults(it)
                } else {
                    view.showFlightResultsEmpty()
                }
            }, {
                view.showFlightResultsError(it.localizedMessage)
            })
        )
    }

    override fun disposeCalls() {
        compositeDisposable.dispose()
    }
}