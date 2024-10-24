package com.example.mazaady.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mazaady.BR

abstract class BaseAdapter<T>(
    private var items: List<T>,
    open val listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    abstract fun bindData(holder: BaseViewHolder, item: T)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        bindData(holder, currentItem)
    }


    class ItemsViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems() = items
    override fun getItemCount(): Int = items.size
}
