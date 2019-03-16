package br.com.skyscannerapplication.view.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.model.entities.out.FlightRequest
import br.com.skyscannerapplication.model.entities.out.FlightResponse
import br.com.skyscannerapplication.model.repository.LivePricingRepositoryImpl
import kotlinx.android.synthetic.main.fragment_search_flights.*
import kotlinx.android.synthetic.main.fragment_search_flights.view.*

class FlightResultsFragment : Fragment(), FlightResultsContract.View {

    private val searchFlightsPresenter = FlightResultsPresenter(LivePricingRepositoryImpl(), this)

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

    val adapter = FlightResultsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_flights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.flightResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        view.flightResultsRecyclerView.adapter = adapter
        searchFlightsPresenter.searchFlights(
            FLIGHT_REQUEST_FORM_DATA
        )

        super.onViewCreated(view, savedInstanceState)
    }

    override fun setProgressIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressIndicator.visibility = View.VISIBLE
        } else {
            progressIndicator.visibility = View.GONE
        }
    }

    override fun showFlightResults(flights: MutableList<FlightResponse>) {
        adapter.setData(flights)
    }

    override fun showFlightResultsEmpty() {
    }

    override fun showFlightResultsError(errorMessage: String) {
    }

    override fun onStop() {
        super.onStop()
        searchFlightsPresenter.disposeCalls()
    }
}