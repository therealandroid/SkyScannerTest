package br.com.skyscannerapplication.model.entities.api

import com.google.gson.annotations.SerializedName

data class Trips(
    @SerializedName("OutboundLegId")
    val outboundLegId: LegId,
    @SerializedName("InboundLegId")
    val inboundLegId: LegId
)