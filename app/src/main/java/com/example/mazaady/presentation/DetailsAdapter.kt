package com.example.mazaady.presentation

import com.example.mazaady.BR
import com.example.mazaady.R
import com.example.mazaady.base.BaseAdapter
import com.example.mazaady.base.BaseInteractionListener
import com.example.mazaady.data.model.PairString
import dagger.hilt.android.AndroidEntryPoint

class DetailsAdapter(list: List<PairString>, listener: IDetails) :
    BaseAdapter<PairString>(list, listener) {
    override val layoutId: Int
        get() = R.layout.list_item_details

    override fun bindData(holder: BaseViewHolder, item: PairString) {
        if (holder is ItemsViewHolder) {
            holder.binding.setVariable(BR.pair, item)
            holder.binding.executePendingBindings()
        }
    }
}

interface IDetails : BaseInteractionListener {
    fun onClick(item: Pair<String, String>)
}