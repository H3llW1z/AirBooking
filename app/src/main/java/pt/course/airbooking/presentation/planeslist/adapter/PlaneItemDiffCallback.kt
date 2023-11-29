package pt.course.airbooking.presentation.planeslist.adapter

import androidx.recyclerview.widget.DiffUtil
import pt.course.airbooking.domain.entity.PlaneType

object PlaneItemDiffCallback: DiffUtil.ItemCallback<PlaneType>() {
    override fun areItemsTheSame(oldItem: PlaneType, newItem: PlaneType): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlaneType, newItem: PlaneType): Boolean =
        oldItem == newItem
}