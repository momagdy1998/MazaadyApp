package com.example.mazaady.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mazaady.base.BaseFragment
import com.example.mazaady.databinding.FragmentDetailsBinding
import com.example.mazaady.presentation.categories.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), IDetails {

    private val detailsAdapter by lazy {
        DetailsAdapter(emptyList(), this)
    }
    private val args by navArgs<DetailsFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.pairsValues.toMutableList()
        detailsAdapter.setItems(data)
        viewBinding.rvDetails.adapter = detailsAdapter

    }

    override fun onClick(item: Pair<String, String>) {

    }


}