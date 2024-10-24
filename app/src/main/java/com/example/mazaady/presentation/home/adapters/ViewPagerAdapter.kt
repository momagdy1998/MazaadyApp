package com.example.mazaady.presentation.home.adapters

import com.example.mazaady.BR
import com.example.mazaady.R
import com.example.mazaady.base.BaseAdapter
import com.example.mazaady.base.BaseInteractionListener
import com.example.mazaady.data.model.home.CourseModel
import com.example.mazaady.databinding.LayoutPagerBinding

class ViewPagerAdapter(
    items: List<CourseModel>,
    override val listener: ICourseDetails
) : BaseAdapter<CourseModel>(items, listener) {
    override val layoutId: Int
        get() = R.layout.layout_pager

    override fun bindData(holder: BaseViewHolder, item: CourseModel) {
        if (holder is ItemsViewHolder) {
            val binding =
                holder.binding as LayoutPagerBinding // Replace with your actual binding class
            binding.setVariable(BR.model, item) // Replace with your variable name
            binding.executePendingBindings()


        }
    }
}


interface ICourseDetails : BaseInteractionListener {
    fun onClick(course: CourseModel)
}