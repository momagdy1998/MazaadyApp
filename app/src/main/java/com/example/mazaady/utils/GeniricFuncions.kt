package com.example.mazaady.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.example.mazaady.R
import com.example.mazaady.presentation.categories.adapters.BaseSpinnerAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun Context.showMessage(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun <T> wrapWithFlow(function: () -> T): Flow<Resources<T>?> {
    return flow {
        try {
            emit(Resources.Loading)
            delay(2000)
            val response = function()
            Log.d("CATCH-Error", "$response ")
            if (response != null)
                emit(Resources.Success(response))
        } catch (e: Exception) {
            Log.d("CATCH-Error", "$e ")
            emit(Resources.Error(e.message.toString()))
        }
    }
}

fun <T> wrapResponseWithFlow(function: suspend () -> Response<T>): Flow<Resources<T?>> {
    return flow {
        try {
            emit(Resources.Loading)
            delay(2000)
            val response = function()
            if (response.isSuccessful) {
                emit(Resources.Success(response.body()))
            } else {
                emit(Resources.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resources.Error(e.message.toString()))
        }
    }
}

fun <T> Context.setupSpinner(
    spinner: Spinner,
    items: List<T>,
    itemSelected: (T) -> Unit,
    itemToString: (T) -> String // Function to convert T to String
) {
    val adapter = BaseSpinnerAdapter(
        this,
        R.layout.item_custom_spinner,
        items,
        itemToString // Pass the conversion function
    )

    spinner.adapter = adapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            if (position >= 0 && position < items.size) {
                val selectedItem = items[position]
                itemSelected(selectedItem)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }
}