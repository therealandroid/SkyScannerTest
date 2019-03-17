package br.com.skyscannerapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.config.AppConstants
import br.com.skyscannerapplication.config.ConfigutarionFile
import br.com.skyscannerapplication.view.flights.FlightResultsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * This is the main Activity
 * it will just manage the fragments
 *
 */
class LaunchActivity : AppCompatActivity(), FlightResultsFragment.FlightResultFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adults = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.adults
        val cabin = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.cabinclass
        val destination = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.destinationplace
        val origin = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.originplace
        val dateOut = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.outbounddate
        val dateIn = ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA.inbounddate

        supportActionBar?.title = "$destination - $origin"
        supportActionBar?.subtitle = "$dateOut - $dateIn, $adults adult, $cabin"
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        makeSearch()
    }

    private fun makeSearch() {
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.BUNDLE_FLIGHT_REQUEST_EXTRA_KEY, ConfigutarionFile.FLIGHT_REQUEST_FORM_DATA)
        val flightResultsFragment = FlightResultsFragment()
        flightResultsFragment.arguments = bundle
        setFragment(flightResultsFragment)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    //Safe attach callback to the fragment
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is FlightResultsFragment) {
            fragment.setFlightResultsCallback(this)
        }
    }

    override fun flightResultCount(totalFlightsFound: Int) {
        totalResults.text = getString(R.string.flight_results, totalFlightsFound, totalFlightsFound)
    }
}