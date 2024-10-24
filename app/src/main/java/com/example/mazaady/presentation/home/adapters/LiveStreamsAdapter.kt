package com.example.mazaady.presentation.home.adapters

import com.example.mazaady.BR
import com.example.mazaady.R
import com.example.mazaady.base.BaseAdapter
import com.example.mazaady.base.BaseInteractionListener
import com.example.mazaady.data.model.home.LiveStream
import com.example.mazaady.databinding.ItemLayoutUserBinding

class LiveStreamsAdapter(
    users: List<LiveStream>,
    override val listener: ILiveStream
) : BaseAdapter<LiveStream>(users, listener) {
    override val layoutId: Int
        get() = R.layout.item_layout_user

    override fun bindData(holder: BaseViewHolder, item: LiveStream) {
        if (holder is ItemsViewHolder) {
            // Cast the binding to the specific binding type
            val binding = holder.binding as ItemLayoutUserBinding
            binding.setVariable(BR.user, item)
            binding.executePendingBindings()

            binding.cardLive.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
}

interface ILiveStream : BaseInteractionListener {
    fun onClick(user: LiveStream)
}