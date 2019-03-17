package br.com.skyscannerapplication.model.apiwrapper

import br.com.skyscannerapplication.config.ConfigutarionFile
import br.com.skyscannerapplication.model.apiwrapper.routes.LivePricingService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * This class manage our retrofit requests to the services
 * If necessary it also provides any intercepting logic for OkHttpClient
 * handling.
 *
 */
object ApiManager {
    var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ConfigutarionFile.LIVE_PRICING_API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //This will make retrofit use Rx Calls instead Retrofit Calls
        .addConverterFactory(GsonConverterFactory.create(gson)) // We delegate to Gson to make the conversions
        .client(OkHttpClient())
        .build()

    private fun <T> provideService(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    val livePricingRoute: LivePricingService = provideService(LivePricingService::class.java)

}
