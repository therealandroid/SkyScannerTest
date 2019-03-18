package br.com.skyscannerapplication

import br.com.skyscannerapplication.mocks.MockFlights
import br.com.skyscannerapplication.model.entities.api.*
import br.com.skyscannerapplication.model.repository.LivePricingRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
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

    var repository = LivePricingRepositoryImpl()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_processFlightResult_shouldBe_Successfully() {
        repository.processFlightResult(MockFlights.mockValidFlight())
    }

    @Test
    fun test_processFlightResult_should_notBroken_withWrongLegsData() {
        repository.processFlightResult(MockFlights.mockFlightItineraryNotContainAnValidLegId())
    }

    @Test
    fun test_processFlightResult_should_notBroken_withWrongCarrierData() {
        repository.processFlightResult(MockFlights.mockFlightItineraryNotContainCarrierInfo())
    }

    @Test
    fun test_processFlightResult_should_notBroken_withWrongSegmentData() {
        repository.processFlightResult(MockFlights.mockFlightItineraryNotContainValidSegmentInfo())
    }
}
