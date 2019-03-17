package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.model.entities.pojo.FlightRequest
import br.com.skyscannerapplication.model.entities.pojo.FlightResult
import br.com.skyscannerapplication.model.entities.pojo.RequestError

interface FlightResultsContract {

    interface Presenter {
        fun searchFlights(flightRequest: FlightRequest)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFlightResults(flights: MutableList<FlightResult>)
        fun showFlightResultsEmpty()
        fun showFlightResultsError(error: RequestError)
    }
}