package pt.course.airbooking.presentation.flightslist.adapter

import androidx.recyclerview.widget.DiffUtil
import pt.course.airbooking.domain.entity.Flight

object FlightItemDiffCallback: DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean =
        oldItem == newItem
}