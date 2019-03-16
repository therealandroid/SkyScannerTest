package br.com.skyscannerapplication.view.flights

import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResponse

interface FlightResultsContract {

    interface Presenter {
        fun searchFlights(flightRequest: FlightRequest)
    }

    interface View {
        fun setProgressIndicator(boolean: Boolean)
        fun showFlightResults(flights: MutableList<FlightResponse>)
        fun showFlightResultsEmpty()
        fun showFlightResultsError(errorMessage: String)
    }
}