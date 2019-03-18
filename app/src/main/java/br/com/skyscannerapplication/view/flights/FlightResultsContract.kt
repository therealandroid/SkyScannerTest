package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.model.pojo.FlightRequest
import br.com.skyscannerapplication.model.pojo.FlightResult
import br.com.skyscannerapplication.model.pojo.RequestError

interface FlightResultsContract {

    interface Presenter {
        fun searchFlights(flightRequest: FlightRequest)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFlightResults(flights: MutableList<FlightResult>)
        fun showFlightResultsEmpty()
        fun showFlightResultsError(requestError: RequestError)
    }
}