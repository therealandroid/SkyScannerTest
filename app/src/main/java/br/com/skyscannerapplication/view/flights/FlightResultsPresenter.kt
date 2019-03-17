package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.model.pojo.FlightRequest
import br.com.skyscannerapplication.model.pojo.RequestError
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import br.com.skyscannerapplication.view.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException


class FlightResultsPresenter(
    private val repository: LivePricingRepository,
    private val view: FlightResultsContract.View
) :
    BasePresenter, FlightResultsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    /**
     * This function make a call to living price Api
     * returning all flights available for the matching input
     *
     */
    override fun searchFlights(flightRequest: FlightRequest) {
        view.showLoading()

        compositeDisposable.add(repository.getFlights(flightRequest)
            .doFinally {
                view.hideLoading()
            }.subscribe({
                if (it.isNotEmpty()) {
                    view.showFlightResults(it)
                } else {
                    view.showFlightResultsEmpty()
                }
            }, {
                view.showFlightResultsError(handleExceptions(it))
            })
        )
    }

    private fun handleExceptions(throwable: Throwable): RequestError {
        return if (throwable is UnknownHostException) {
            RequestError(
                R.string.no_internet_connection,
                R.drawable.ic_no_internet
            )
        } else {
            RequestError(
                R.string.no_flights_found,
                R.drawable.ic_no_results
            )
        }
    }

    override fun disposeCalls() {
        compositeDisposable.dispose()
    }
}