package br.com.skyscannerapplication.view.flights

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.model.entities.out.FlightResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_flight_result.view.*
import kotlinx.android.synthetic.main.item_flight_result_icon_line.view.*
import java.text.SimpleDateFormat


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

    /**
     * All ID's for this item is identifiable
     * by I1_ prefix
     *
     */
    inner class FlightResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { }
        }

        fun minutesToPeriod(time: Long): String {
            val days = (time / 24 / 60)
            val hours = time / 60 % 24
            val minutes = time % 60

            return if (days > 0) {
                "$days d $hours h $minutes m"
            } else if (hours > 0) {
                "$hours h $minutes m"
            } else {
                "$hours h $minutes m"
            }
        }

        fun bind(flightItem: FlightResult) {
            //OutBound flight info
            itemView.outboundCarrierDisplayCodeAndName.text =
                "${flightItem.arriveFlightInfo.originAirport}-${flightItem.arriveFlightInfo.destinyAirport}," +
                        " ${flightItem.arriveFlightInfo.carrierName}"

            val outboundDateFormatter = SimpleDateFormat("HH:mm")
            val outboundDepartureTime = outboundDateFormatter.format(flightItem.arriveFlightInfo.departure)
            val outboundArriveTime = outboundDateFormatter.format(flightItem.arriveFlightInfo.arrival)

            itemView.outBoundDepartureAndArrivalTime.text = "$outboundDepartureTime - $outboundArriveTime"

            itemView.outBoundDirectionType.text = flightItem.arriveFlightInfo.direction
            val outboundTotalFlightDuration = minutesToPeriod(flightItem.arriveFlightInfo.duration!!)
            itemView.outBoundTotalFlightDuration.text = outboundTotalFlightDuration
            Picasso.get().load(flightItem.outFlightInfo.carrierImageUrl).into(itemView.outBoundCarrierImage)

            //InBound flight info
            Picasso.get().load(flightItem.arriveFlightInfo.carrierImageUrl).into(itemView.inBoundCarrierImage)
            itemView.inBoundCarrierDisplayCodeAndName.text =
                "${flightItem.arriveFlightInfo.originAirport}-${flightItem.arriveFlightInfo.destinyAirport}," +
                        " ${flightItem.arriveFlightInfo.carrierName}"

            val inboundDateFormatter = SimpleDateFormat("HH:mm")
            val inboundDepartureTime = inboundDateFormatter.format(flightItem.arriveFlightInfo.departure)
            val inboundArriveTime = inboundDateFormatter.format(flightItem.arriveFlightInfo.arrival)

            itemView.inBoundDepartureAndArrivalTime.text = "$inboundDepartureTime - $inboundArriveTime"

            itemView.inBoundDirectionType.text = flightItem.arriveFlightInfo.direction
            val inboundTotalFlightDuration = minutesToPeriod(flightItem.arriveFlightInfo.duration)
            itemView.inBoundTotalFlightDuration.text = inboundTotalFlightDuration

            itemView.flightResultPrice.text = "${flightItem.currencySymbol} ${flightItem.arriveFlightInfo.price}"
            itemView.flightResultSite.text = "www.jet.com"

            val randomNote = (0..10).random()
            itemView.I1_rateText.text = "$randomNote"

            if (randomNote >= 6) {
                Picasso.get().load(R.drawable.ic_face_smile).into(itemView.I1_rateIcon)
            } else {
                Picasso.get().load(R.drawable.ic_face_poker_face).into(itemView.I1_rateIcon)
            }
        }
    }
}