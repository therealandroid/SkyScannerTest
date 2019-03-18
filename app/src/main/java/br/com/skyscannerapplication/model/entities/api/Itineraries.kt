package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Itineraries(
    @SerializedName("OutboundLegId")
    val outBoundLegId: String? = "",
    @SerializedName("InboundLegId")
    val inboundLegId: String? = "",
    @SerializedName("PricingOptions")
    val pricingOptions: List<PricingOptions>? = mutableListOf()


)