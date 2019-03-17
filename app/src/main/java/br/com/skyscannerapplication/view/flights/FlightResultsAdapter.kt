package br.com.skyscannerapplication.view.flights

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.extensions.minutesToPeriod
import br.com.skyscannerapplication.model.entities.pojo.FlightResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_flight_result.view.*
import kotlinx.android.synthetic.main.item_flight_result_icon_line.view.*
import java.text.SimpleDateFormat
import java.util.*


class FlightResultsAdapter : Adapter<FlightResultsAdapter.FlightResultViewHolder>() {

    var flightItems = mutableListOf<FlightResult>()

    fun setData(flightItems: MutableList<FlightResult>) {
        this.flightItems = flightItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightResultViewHolder {
        return FlightResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_flight_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return flightItems.size
    }

    override fun onBindViewHolder(holder: FlightResultViewHolder, position: Int) {
        holder.bind(flightItems[position])
    }

    inner class FlightResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(flightItem: FlightResult) {
            val context = itemView.context
            val dateFormatter = SimpleDateFormat(context.getString(R.string.time_format), Locale(flightItem.locale))

            //////////////////////////////////////////////////////////////////
            /////////////OutBound flight info binding/////////////////////////

            itemView.outboundCarrierInfo.text =
                context.getString(
                    R.string.carrier_info,
                    flightItem.outBoundFlightInfo.originAirport,
                    flightItem.outBoundFlightInfo.destinyAirport,
                    flightItem.outBoundFlightInfo.carrierName
                )

            val outboundDepartureTime = dateFormatter.format(flightItem.outBoundFlightInfo.departure)
            val outboundArriveTime = dateFormatter.format(flightItem.outBoundFlightInfo.arrival)

            itemView.outBoundDepartureAndArrivalTime.text =
                context.getString(
                    R.string.departure_and_arrival_time,
                    outboundDepartureTime,
                    outboundArriveTime
                )

            itemView.outBoundDirectionType.text = flightItem.outBoundFlightInfo.direction
            val outboundTotalFlightDuration = flightItem.outBoundFlightInfo.duration!!.minutesToPeriod() //TODO fix !!
            itemView.outBoundTotalFlightDuration.text = outboundTotalFlightDuration
            Picasso.get().load(flightItem.outBoundFlightInfo.carrierImageUrl).into(itemView.outBoundCarrierImage)

            ////////////////////////////////////////////////////////////
            //////////////////InBound flight info binding///////////////
            Picasso.get().load(flightItem.inBoundFlightInfo.carrierImageUrl).into(itemView.inBoundCarrierImage)

            itemView.inBoundCarrierInfo.text =
                context.getString(
                    R.string.carrier_info,
                    flightItem.inBoundFlightInfo.originAirport,
                    flightItem.inBoundFlightInfo.destinyAirport,
                    flightItem.inBoundFlightInfo.carrierName
                )

            val inboundDepartureTime = dateFormatter.format(flightItem.inBoundFlightInfo.departure)
            val inboundArriveTime = dateFormatter.format(flightItem.inBoundFlightInfo.arrival)

            itemView.inBoundDepartureAndArrivalTime.text =
                context.getString(
                    R.string.departure_and_arrival_time,
                    inboundDepartureTime,
                    inboundArriveTime
                )

            itemView.inBoundDirectionType.text = flightItem.inBoundFlightInfo.direction
            val inboundTotalFlightDuration = flightItem.inBoundFlightInfo.duration!!.minutesToPeriod() //TODO
            itemView.inBoundTotalFlightDuration.text = inboundTotalFlightDuration

            itemView.flightResultPrice.text = context.getString(
                R.string.flight_price,
                flightItem.currencySymbol,
                flightItem.inBoundFlightInfo.price
            )

            itemView.flightResultSite.text = context.getString(R.string.skyscanner_website)

            setRandomRating(itemView.rateTripIcon, itemView.userRatingText)
        }

        private fun setRandomRating(iconView: ImageView, rateTextView: TextView) {
            val randomNote = (0..10).random()
            rateTextView.text = "$randomNote"

            val iconRes: Int

            iconRes = if (randomNote >= 6) {
                R.drawable.ic_face_smile
            } else {
                R.drawable.ic_face_poker_face
            }

            Picasso.get().load(iconRes).into(iconView)
        }
    }
}