package com.example.mazaady.presentation.categories.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mazaady.R

class BaseSpinnerAdapter<T>(
    context: Context,
    private val layoutResourceId: Int,
    private val items: List<T>,
    private val itemToString: (T) -> String // Function to convert T to String
) : ArrayAdapter<T>(context, layoutResourceId, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv_title) // Adjust if your layout uses a different TextView ID

        textView.text = itemToString(items[position]) // Use the conversion function

        return view
    }
}