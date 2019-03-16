package br.com.skyscannerapplication.view.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResult
import br.com.skyscannerapplication.model.repository.LivePricingRepositoryImpl
import kotlinx.android.synthetic.main.fragment_search_flights.*

class SearchFlightsFragment : Fragment(), SearchFlightsContract.View {

    private val searchFlightsPresenter = SearchFlightsPresenter(LivePricingRepositoryImpl(), this)

    private val FLIGHT_REQUEST_FORM_DATA = FlightRequest(
        cabinclass = "Economy",
        country = "UK",
        currency = "GBP",
        locale = "en-GB",
        locationschema = "sky",
        originplace = "EDI-sky",
        destinationplace = "LOND-sky",
        outbounddate = "2019-03-18", //Go on next monday
        inbounddate = "2019-03-19", //back a day after
        adults = 1,
        children = 0,
        infants = 0,
        apikey = "ss630745725358065467897349852985"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_flights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        flightRequest.setOnClickListener {
            searchFlightsPresenter.searchFlights(
                FLIGHT_REQUEST_FORM_DATA
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun setProgressIndicator(boolean: Boolean) {
    }

    override fun showFlightResults(flights: MutableList<FlightResult>) {
    }

    override fun showFlightResultsEmpty() {
    }

    override fun showFlightResultsError(errorMessage: String) {
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        searchFlightsPresenter.disposeCalls()
    }
}