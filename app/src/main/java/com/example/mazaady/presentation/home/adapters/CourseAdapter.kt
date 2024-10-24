package com.example.mazaady.presentation.home.adapters

import com.example.mazaady.BR
import com.example.mazaady.R
import com.example.mazaady.base.BaseAdapter
import com.example.mazaady.base.BaseInteractionListener
import com.example.mazaady.data.model.home.Course
import com.example.mazaady.databinding.ListItemCoursesBinding

class CourseAdapter(
    items: List<Course>,
    override val listener: ICourses
) : BaseAdapter<Course>(items, listener) {

    private var selectedPosition: Int = 0


    override val layoutId: Int
        get() = R.layout.list_item__courses

    override fun bindData(holder: BaseViewHolder, item: Course) {
        if (holder is ItemsViewHolder) {
            holder.binding.setVariable(BR.course, item)
            holder.binding.executePendingBindings()

            // Handle selection background
            if (holder.adapterPosition == selectedPosition) {
                holder.binding.apply {
                    root.setBackgroundResource(R.drawable.course_select_bg)
                    (this as ListItemCoursesBinding).textCourseName.setTextColor(
                        root.context.getColor(R.color.white)
                    )
                }
            } else {
                holder.binding.apply {
                    root.setBackgroundResource(R.drawable.course_unselect_bg)
                    (this as ListItemCoursesBinding).textCourseName.setTextColor(
                        holder.binding.root.context.getColor(
                            R.color.dark_gray_color
                        )
                    )
                }
            }

            // Set click listener to change selection
            holder.binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(previousPosition) // Unselect previous item
                notifyItemChanged(selectedPosition) // Select the new item
                listener.onClick(getItems()[selectedPosition])
            }
        }
    }


}

interface ICourses : BaseInteractionListener {
    fun onClick(course: Course)
}
