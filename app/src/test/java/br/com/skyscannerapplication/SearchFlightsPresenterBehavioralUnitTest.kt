package br.com.skyscannerapplication

import br.com.skyscannerapplication.mocks.MockedData
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import br.com.skyscannerapplication.view.flights.SearchFlightsContract
import br.com.skyscannerapplication.view.flights.SearchFlightsPresenter
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 */
@RunWith(MockitoJUnitRunner::class)
class SearchFlightsPresenterBehavioralUnitTest {

    @Mock lateinit var repository: LivePricingRepository
    @Mock lateinit var view: SearchFlightsContract.View

    lateinit var searchFlightsPresenter: SearchFlightsPresenter

    @Before
    fun setup() {
        ImediateSchedulers.setImediateExecutor()
        MockitoAnnotations.initMocks(this)
        searchFlightsPresenter = SearchFlightsPresenter(repository, view)
    }

    @Test
    fun test_searchFlight_shouldHandle_empty() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.just(mutableListOf()))
        searchFlightsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).setProgressIndicator(true) //make sure this method is being called first
        verify(view).showFlightResultsEmpty()
        verify(view).setProgressIndicator(false) //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_success() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.just(MockedData.MANY_FLIGHT_RESULTS))
        searchFlightsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).setProgressIndicator(true) //make sure this method is being called first
        verify(view).showFlightResults(MockedData.MANY_FLIGHT_RESULTS)
        verify(view).setProgressIndicator(false) //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_error() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.error(Throwable()))
        searchFlightsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).setProgressIndicator(true) //make sure this method is being called first
        // This message should be the same as you display in your R.string.error in the presenter call
        verify(view).showFlightResultsError("THIS IS A ERROR!!")
        verify(view).setProgressIndicator(false) //make sure this method is being called at end
    }

}
