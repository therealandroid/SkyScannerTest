package br.com.skyscannerapplication.view.flights

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.skyscannerapplication.R
import br.com.skyscannerapplication.model.entities.out.FlightResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_flight_result.view.*
import kotlinx.android.synthetic.main.item_flight_result_icon_line.view.*

class FlightResultsAdapter : Adapter<FlightResultsAdapter.FlightResultViewHolder>() {

    var flightItems = mutableListOf<FlightResponse>()

    fun setData(flightItems: MutableList<FlightResponse>) {
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

        fun bind(flightItem: FlightResponse) {
            Picasso.get().load("https://s1.apideeplink.com/images/airlines/EZ.png").into(itemView.I1_carrierImage)

//            itemView.I1_directionType
//            itemView.I1_totalFlightDuration
//            itemView.I1_site
//            itemView.I1_rateIcon
//            itemView.I1_rateText
//            itemView.I1_price
        }
    }
}