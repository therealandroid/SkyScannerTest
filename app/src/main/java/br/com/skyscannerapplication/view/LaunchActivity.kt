package br.com.skyscannerapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.view.flights.FlightResultsFragment

/**
 * This is the main Activity
 * it will just manage the fragments
 *
 */
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(FlightResultsFragment())
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }
}