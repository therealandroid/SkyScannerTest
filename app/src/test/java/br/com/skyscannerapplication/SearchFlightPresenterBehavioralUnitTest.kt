package br.com.skyscannerapplication

import br.com.skyscannerapplication.mocks.MockedData
import br.com.skyscannerapplication.model.pojo.RequestError
import br.com.skyscannerapplication.model.repository.LivePricingRepository
import br.com.skyscannerapplication.view.flights.FlightResultsContract
import br.com.skyscannerapplication.view.flights.FlightResultsPresenter
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
class SearchFlightPresenterBehavioralUnitTest {

    @Mock
    lateinit var repository: LivePricingRepository
    @Mock
    lateinit var view: FlightResultsContract.View

    lateinit var flightResultsPresenter: FlightResultsPresenter

    @Before
    fun setup() {
        ImediateSchedulers.setImediateExecutor()
        MockitoAnnotations.initMocks(this)
        flightResultsPresenter = FlightResultsPresenter(repository, view)
    }

    @Test
    fun test_searchFlight_shouldHandle_empty() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.just(mutableListOf()))
        flightResultsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).showLoading() //make sure this method is being called first
        verify(view).showFlightResultsEmpty()
        verify(view).hideLoading() //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_success() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.just(MockedData.MANY_FLIGHT_RESULTS))
        flightResultsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).showLoading() //make sure this method is being called first
        verify(view).showFlightResults(MockedData.MANY_FLIGHT_RESULTS)
        verify(view).hideLoading() //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_error() {
        `when`(repository.getFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.error(Throwable()))
        flightResultsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).showLoading() //make sure this method is being called first
        // This message should be the same as you display in your R.string.error in the presenter call
        verify(view).showFlightResultsError(RequestError(0, 0))
        verify(view).hideLoading() //make sure this method is being called at end
    }

}
