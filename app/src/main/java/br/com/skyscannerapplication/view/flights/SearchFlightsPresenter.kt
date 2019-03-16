package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import br.com.skyscannerapplication.view.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SearchFlightsPresenter(
    private val repository: LivePricingRepository,
    private val view: SearchFlightsContract.View
) :
    BasePresenter, SearchFlightsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    /**
     * This function make a call to living price Api
     * returning all flights available for the matching input
     *
     */
    override fun searchFlights(flightRequest: FlightRequest) {
        view.setProgressIndicator(true)

        compositeDisposable.add(repository.getFlights(flightRequest)
            .doFinally {
                view.setProgressIndicator(false)
            }.subscribe({
                if (it.isNotEmpty()) {
                    view.showFlightResults(it)
                } else {
                    view.showFlightResultsEmpty()
                }
            }, {
                //TODO make sure it calls R.string.<your_error_message> instead hardcoded
                view.showFlightResultsError("THIS IS A ERROR!!")
            })
        )
    }

    override fun disposeCalls() {
        compositeDisposable.dispose()
    }
}