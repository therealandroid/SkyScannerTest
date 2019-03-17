package br.com.skyscannerapplication.view.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.config.AppConstants
import br.com.skyscannerapplication.model.pojo.FlightRequest
import br.com.skyscannerapplication.model.pojo.FlightResult
import br.com.skyscannerapplication.model.pojo.RequestError
import br.com.skyscannerapplication.model.repository.LivePricingRepositoryImpl
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.alert_view.*
import kotlinx.android.synthetic.main.fragment_search_flights.view.*


class FlightResultsFragment : Fragment(), FlightResultsContract.View {

    interface FlightResultFragmentCallback {
        fun flightResultCount(totalFlightsFound: Int)
    }

    private var callback: FlightResultsFragment.FlightResultFragmentCallback? = null

    private val searchFlightsPresenter = FlightResultsPresenter(LivePricingRepositoryImpl(), this)

    private val adapter = FlightResultsAdapter()
    private var skeletonView: RecyclerViewSkeletonScreen? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_flights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.flightResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        view.flightResultsRecyclerView.adapter = adapter

        val flightRequest = arguments?.getSerializable(AppConstants.BUNDLE_FLIGHT_REQUEST_EXTRA_KEY)

        searchFlightsPresenter.searchFlights(flightRequest as FlightRequest)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun showLoading() {
        skeletonView = Skeleton.bind(view?.flightResultsRecyclerView)
            .adapter(adapter)
            .load(R.layout.item_flight_result_skeleton)
            .color(R.color.skeletonViewShimmerColor)
            .duration(1200)
            .show()
    }

    override fun hideLoading() {
        skeletonView?.hide()
    }

    override fun showFlightResults(flights: MutableList<FlightResult>) {
        callback?.flightResultCount(flights.size) // Update activity result
        adapter.setData(flights)
    }

    override fun showFlightResultsEmpty() {
        alertView.visibility = View.VISIBLE
        alertViewTitle.text = getString(R.string.no_flights_found)
        Picasso.get().load(R.drawable.ic_no_results).into(alertViewIcon)
    }

    override fun showFlightResultsError(requestError: RequestError) {
        alertView.visibility = View.VISIBLE
        alertViewTitle.text = getString(requestError.resMessage)
        Picasso.get().load(requestError.resIcon).into(alertViewIcon)
    }

    override fun onStop() {
        super.onStop()
        searchFlightsPresenter.disposeCalls()
    }

    fun setFlightResultsCallback(callback: FlightResultFragmentCallback) {
        this.callback = callback
    }
}