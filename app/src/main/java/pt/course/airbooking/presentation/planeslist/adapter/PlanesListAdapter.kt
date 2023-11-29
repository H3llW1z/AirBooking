package pt.course.airbooking.presentation.planeslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pt.course.airbooking.R
import pt.course.airbooking.databinding.ItemPlaneBinding
import pt.course.airbooking.domain.entity.PlaneType
import java.util.Locale

class PlanesListAdapter : ListAdapter<PlaneType, PlaneItemViewHolder>(PlaneItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaneItemViewHolder {

        val binding =
            ItemPlaneBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlaneItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaneItemViewHolder, position: Int) {

        val item = currentList[position]

        with(holder.binding) {
            name.text = item.name
            capacity.text = String.format(
                Locale.getDefault(),
                root.context.getString(R.string.capacity_template),
                item.capacity
            )
            textViewDescription.text = item.description
        }
    }
}