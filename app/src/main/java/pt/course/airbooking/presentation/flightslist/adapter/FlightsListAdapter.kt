package pt.course.airbooking.presentation.flightslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pt.course.airbooking.databinding.ItemFlightBinding
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.presentation.formatter.getTimeFormatter
import java.text.SimpleDateFormat
import java.util.Locale

class FlightsListAdapter : ListAdapter<Flight, FlightItemViewHolder>(FlightItemDiffCallback) {

    private val dateFormatter = SimpleDateFormat("MMM\ndd", Locale.getDefault())
    private val timeFormatter = getTimeFormatter()

    var onItemClick: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightItemViewHolder {

        val binding =
            ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FlightItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightItemViewHolder, position: Int) {

        val item = currentList[position]

        with(holder.binding) {
            departureLocation.text = item.departureLocation
            departureLocation.isSelected = true
            destinationLocation.text = item.destinationLocation
            calendarDate.text = dateFormatter.format(item.departureTimestamp)
            departureTime.text = timeFormatter.format(item.departureTimestamp)
            destinationTime.text = timeFormatter.format(item.arrivalTimestamp)
            departureAirportCode.text = item.departureAirportCode
            destinationAirportCode.text = item.destinationAirportCode
            cardViewFlightItem.setOnClickListener {
                onItemClick?.let { listener ->
                    listener(item.id)
                }
            }
        }
    }
}