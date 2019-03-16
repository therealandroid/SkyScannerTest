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
 */
@RunWith(MockitoJUnitRunner::class)
class SearchFlightsPresenterUnitTest {

    lateinit var searchFlightsPresenter: SearchFlightsPresenter

    @Mock
    lateinit var repository: LivePricingRepository

    @Mock
    lateinit var view: SearchFlightsContract.View

    @Before
    fun setup() {
        ImediateSchedulers.setImediateExecutor()
        MockitoAnnotations.initMocks(this)
        searchFlightsPresenter = SearchFlightsPresenter(repository, view)
    }

    @Test
    fun test_searchFlight_shouldBe_empty() {
        `when`(repository.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)).thenReturn(Single.just(mutableListOf()))
        searchFlightsPresenter.searchFlights(MockedData.SAMPLE_FLIGHT_REQUEST)
        verify(view).setProgressIndicator(true) //make sure this method is being called first
        verify(view).showFlightResultsEmpty()
        verify(view).setProgressIndicator(false) //make sure this method is being called at end
    }


}
