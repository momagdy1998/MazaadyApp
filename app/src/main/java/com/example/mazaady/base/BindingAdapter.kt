package com.example.mazaady.base

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.example.mazaady.R
import com.example.mazaady.utils.Resources

@BindingAdapter(value = ["app:setList"])
fun <T> setDataInRecyclerView(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        Log.d("DataInRecycler", "Item NOT NULL ")
        (view.adapter as BaseAdapter<T>).setItems(items)
    } else {
        Log.d("DataInRecycler", "Item IS NULL ")
        (view.adapter as BaseAdapter<T>).setItems(emptyList())
    }
}


@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, Resources: Resources<T>?) {
    if (Resources is Resources.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, Resources: Resources<T>?) {
    if (Resources is Resources.Error) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, Resources: Resources<T>?) {
    if (Resources is Resources.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun setImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView).load(imageUrl).fitCenter().into(imageView)
}

@BindingAdapter(value = ["app:imageUrl"])
fun setImage(imageView: ImageView, imageUrl: Int?) {
    Glide.with(imageView).load(imageUrl).load(imageUrl)
        .placeholder(android.R.color.transparent) // Use a transparent placeholder
        .error(android.R.color.transparent) // Use a transparent error image
        .fitCenter()
        .into(imageView)
}

@BindingAdapter(value = ["app:loadImage"])
fun loadImage(imageView: ImageView, imageUrl: Int?) {
    imageView.load(imageUrl!!) {
        // Apply transformations if needed
        transformations(CircleCropTransformation())
        placeholder(R.color.white)
        error(R.color.white)
        crossfade(true)
        size(300, 300) // Specify desired size
    }
}


