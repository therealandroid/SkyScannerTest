package br.com.skyscannerapplication

import br.com.skyscannerapplication.mocks.MockFlightRequests
import br.com.skyscannerapplication.model.pojo.FlightInfo
import br.com.skyscannerapplication.model.pojo.FlightResult
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
import java.net.UnknownHostException
import java.text.SimpleDateFormat


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 */
@RunWith(MockitoJUnitRunner::class)
class LivePricingRepositoryImplUnitTest {

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
    fun test_searchFlight_shouldHandle_emptyResults() {
        `when`(repository.getFlights(MockFlightRequests.validRequest)).thenReturn(Single.just(mutableListOf()))
        flightResultsPresenter.searchFlights(MockFlightRequests.validRequest)
        verify(view).showLoading() //make sure this method is being called first
        verify(view).showFlightResultsEmpty()
        verify(view).hideLoading() //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_success() {
        val results = mutableListOf(
            FlightResult(
                FlightInfo(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2019-03-18T06:25:00"),
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2019-03-18T07:50:00"),
                    80,
                    "Azul",
                    "https://logos.skyscnr.com/images/airlines/favicon/EZ.png",
                    "LDN-sky",
                    "Directly",
                    300.0F,
                    "GYN",
                    "GRU"
                ),
                FlightInfo(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2019-03-19T06:25:00"),
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2019-03-19T07:50:00"),
                    80,
                    "Azul",
                    "https://logos.skyscnr.com/images/airlines/favicon/EZ.png",
                    "LDN-sky",
                    "Directly",
                    300.0F,
                    "GYN",
                    "GRU"
                ),
                "$",
                "US"
            )
        )

        `when`(repository.getFlights(MockFlightRequests.validRequest)).thenReturn(
            Single.just(results)
        )

        flightResultsPresenter.searchFlights(MockFlightRequests.validRequest)
        verify(view).showLoading() //make sure this method is being called first
        verify(view).showFlightResults(results)
        verify(view).hideLoading() //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_InternetError() {
        `when`(repository.getFlights(MockFlightRequests.validRequest)).thenReturn(Single.error(UnknownHostException("No Host Address associated with this domain.")))
        flightResultsPresenter.searchFlights(MockFlightRequests.validRequest)
        verify(view).showLoading() //make sure this method is being called first
        // This message should be the same as you display in your R.string.error in the presenter call
        verify(view).showFlightResultsError(RequestError(R.string.no_internet_connection, R.drawable.ic_no_internet))
        verify(view).hideLoading() //make sure this method is being called at end
    }

    @Test
    fun test_searchFlight_shouldHandle_AnyThrowable() {
        `when`(repository.getFlights(MockFlightRequests.validRequest)).thenReturn(Single.error(Throwable("I DARE YOU HANDLE THIS EXCEPTION!!!")))
        flightResultsPresenter.searchFlights(MockFlightRequests.validRequest)
        verify(view).showLoading() //make sure this method is being called first
        // This message should be the same as you display in your R.string.error in the presenter call
        verify(view).showFlightResultsError(RequestError(R.string.no_flights_found, R.drawable.ic_no_results))
        verify(view).hideLoading() //make sure this method is being called at end
    }

}
