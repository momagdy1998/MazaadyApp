package com.example.mazaady.presentation.categories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.mazaady.data.model.allPropertiesResponse.Data
import com.example.mazaady.databinding.ListItemPropertyBinding
import com.example.mazaady.utils.setupSpinner

class PropertiesAdapter(private val onItemSelected: (Data, String) -> Unit) :
    RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder>() {

    private var properties: List<Data?> = emptyList()
    private val otherValues = mutableMapOf<Int, String>()

    fun submitList(newProperties: List<Data?>) {
        properties = newProperties
        otherValues.clear()
        properties.forEachIndexed { index, property ->
            // Initialize with empty string or default value
            otherValues[index] = ""
        }
        notifyDataSetChanged()
    }
    fun getItemAt(position: Int): Data? {
        return if (position in properties.indices) {
            properties[position]
        } else {
            null
        }
    }

    fun getOtherValueAt(position: Int): String {
        return otherValues[position] ?: ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding =
            ListItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property,position, onItemSelected,)
    }

    override fun getItemCount(): Int = properties.size

    inner class PropertyViewHolder(private val binding: ListItemPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Data?, position: Int, onItemSelected: (Data, String) -> Unit) {
            binding.propertyName.text = property?.name

            property?.let {
                val options = it.options ?: emptyList()

                binding.root.context.setupSpinner(
                    binding.spinnerOptions,
                    options,
                    itemSelected = { selectedOption ->
                        val selectedName = selectedOption?.name ?: ""
                        onItemSelected(property, selectedName)

                        // Show or hide the EditText and button based on selection
                        if (selectedName == "Other") {
                            binding.otherInputField.visibility = View.VISIBLE
                            binding.btnSubmitOption.visibility = View.VISIBLE
                        } else {
                            binding.otherInputField.visibility = View.GONE
                            binding.btnSubmitOption.visibility = View.GONE
                            otherValues[position] = selectedName // Store selected value
                        }
                    },
                    itemToString = { option -> option?.name ?: "" }
                )

                // Set the EditText text to the corresponding other value
                binding.otherInputField.setText(otherValues[position] ?: "")
                binding.otherInputField.addTextChangedListener {
                    otherValues[position] = it.toString() // Update the value as the user types
                }

                // Handle button click to save "Other" value
                binding.btnSubmitOption.setOnClickListener {
                    val otherValue = binding.otherInputField.text.toString()
                    otherValues[position] = otherValue // Store the "Other" value
                    Toast.makeText(binding.root.context, "Value saved: $otherValue", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    }