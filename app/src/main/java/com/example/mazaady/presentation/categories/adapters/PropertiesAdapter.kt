package com.example.mazaady.presentation.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazaady.data.model.allPropertiesResponse.Data
import com.example.mazaady.databinding.ListItemPropertyBinding
import com.example.mazaady.utils.setupSpinner

class PropertiesAdapter(private val onItemSelected: (Data, String) -> Unit) :
    RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder>() {

    private var properties: List<Data?> = emptyList()

    fun submitList(newProperties: List<Data?>) {
        properties = newProperties
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding =
            ListItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property, onItemSelected)
    }

    override fun getItemCount(): Int = properties.size

    inner class PropertyViewHolder(private val binding: ListItemPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Data?, onItemSelected: (Data, String) -> Unit) {
            binding.propertyName.text = property?.name

            property?.let {
                binding.root.context.setupSpinner(
                    binding.spinnerOptions,
                    it.options ?: emptyList(),
                    itemSelected = { selectedOption ->
                        onItemSelected(property, selectedOption?.name ?: "")
                    },
                    itemToString = { option -> option?.name ?: "" }
                )
            }
        }
    }
}