package com.example.mazaady.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mazaady.R
import com.example.mazaady.base.BaseFragment
import com.example.mazaady.data.model.home.Course
import com.example.mazaady.data.model.home.CourseModel
import com.example.mazaady.data.model.home.LiveStream
import com.example.mazaady.databinding.FragmentHomeBinding
import com.example.mazaady.presentation.home.adapters.CourseAdapter
import com.example.mazaady.presentation.home.adapters.ICourseDetails
import com.example.mazaady.presentation.home.adapters.ICourses
import com.example.mazaady.presentation.home.adapters.ILiveStream
import com.example.mazaady.presentation.home.adapters.LiveStreamsAdapter
import com.example.mazaady.presentation.home.adapters.ViewPagerAdapter
import com.example.mazaady.presentation.home.adapters.ZoomOutPageTransformer
import com.example.mazaady.utils.Resources
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ICourses, ILiveStream, ICourseDetails {

    protected val viewModel by viewModels<HomeViewModel>()


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.viewModel = viewModel
        setUpCoursesAdapter()
        setUpliveStreamAdapter()
        setUpNavView()
        setUpPagerWithIndicators()


    }

    private fun setUpPagerWithIndicators() {
        val adapter = ViewPagerAdapter(mutableListOf(), this)
        viewBinding.viewPager.adapter = adapter
        viewBinding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        viewBinding.dotsIndicator.setViewPager2(viewBinding.viewPager)
        viewModel.coursesDetails.observe(viewLifecycleOwner) { result ->
            if (result is Resources.Success) {
                adapter.setItems(result.data)
            }
        }

    }

    private fun setUpNavView() {
        viewBinding.bottomNavView.selectedItemId = R.id.home
        val badgeDrawable: BadgeDrawable = viewBinding.bottomNavView.getOrCreateBadge(R.id.message)
        badgeDrawable.isVisible = true
        badgeDrawable.number = 2
        viewBinding.bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Handle Home action
                    true
                }

                R.id.discover -> {
                    true
                }

                R.id.message -> {

                    true
                }

                R.id.profile -> {

                    true
                }

                else -> false
            }
        }
    }

    private fun setUpliveStreamAdapter() {
        val adapter = LiveStreamsAdapter(mutableListOf(), this)
        viewBinding.rvLiveStream.adapter = adapter
        viewModel.peoples.observe(viewLifecycleOwner) { result ->
            Log.d("HomeFragment", "onViewCreated: $result")
            if (result is Resources.Success) {
                adapter.setItems(result.data)
            }
        }
    }

    private fun setUpCoursesAdapter() {
        val adapter = CourseAdapter(mutableListOf(), this)
        viewBinding.rvCourses.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) { result ->
            Log.d("HomeFragment", "onViewCreated: $result")
            if (result is Resources.Success) {
                adapter.setItems(result.data)
            }
        }
    }

    override fun onClick(course: Course) {

    }

    override fun onClick(user: LiveStream) {

    }

    override fun onClick(course: CourseModel) {

    }


}