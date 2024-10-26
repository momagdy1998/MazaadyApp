package com.example.mazaady.presentation.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mazaady.R
import com.example.mazaady.base.BaseFragment
import com.example.mazaady.data.model.PairString
import com.example.mazaady.data.model.allCategoriesResponse.Category
import com.example.mazaady.data.model.allCategoriesResponse.Children
import com.example.mazaady.data.model.allPropertiesResponse.Data
import com.example.mazaady.data.model.allPropertiesResponse.Option
import com.example.mazaady.databinding.FragmentCategoriesBinding
import com.example.mazaady.presentation.categories.adapters.PropertiesAdapter
import com.example.mazaady.utils.Resources
import com.example.mazaady.utils.setupSpinner
import com.example.mazaady.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    private val viewModel by viewModels<CategoriesViewModel>()
    private lateinit var propertiesAdapter: PropertiesAdapter


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSubmitButton()
        propertiesAdapter = PropertiesAdapter { selectedProperty, selectedOption ->
        }
        viewBinding.recyclerViewProperties.adapter = propertiesAdapter
        viewBinding.spinnerSubCategory.isEnabled = false
        viewModel.getAllCategories()
        setupMainCategorySpinner(emptyList())
        lifecycleScope.launch {
            viewModel.allCategory.collect { resource ->
                Log.d("CollectData", "onViewCreated: ${resource.toData()}")
                when (resource) {
                    is Resources.Error -> {
                        viewBinding.mainCategoriesProgress.visibility = View.GONE
                        requireContext().showMessage(resource.message)
                    }

                    Resources.Loading -> {
                        viewBinding.mainCategoriesProgress.visibility = View.VISIBLE
                    }

                    is Resources.Success -> {
                        viewBinding.mainCategoriesProgress.visibility = View.GONE
                        setupMainCategorySpinner(resource.data?.data?.categories ?: emptyList())
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.allProperty.collect { result ->
                when (result) {
                    is Resources.Error -> {}
                    Resources.Loading -> {

                    }

                    is Resources.Success -> {
                        val propertiesWithOther = result.data?.data?.map { property ->
                            property?.copy(
                                options = property.options?.plus(
                                    Option(
                                        child = false, // Default value
                                        id = null, // Can be null or some other default value
                                        name = "Other",
                                        parent = null, // Default or null value
                                        slug = "other" // Default or any other value you choose
                                    )
                                )
                            )
                        }
                        propertiesAdapter.submitList(propertiesWithOther ?: emptyList())
                    }
                }
            }
        }
    }


    // Optionally, add a submit button listener to gather selected values
    private fun setupSubmitButton() {
        viewBinding.buttonSubmit.setOnClickListener {
            submitValues()

        }
    }

    private fun setupMainCategorySpinner(mainCategories: List<Category?>) {
        requireContext().setupSpinner(
            viewBinding.spinnerMainCategory,
            mainCategories,
            itemSelected = { selectedCategory ->
                setupSubCategorySpinner(selectedCategory?.children ?: emptyList())
            },
            itemToString = { it?.name ?: "" } // Define how to get the string representation
        )
    }

    private fun setupSubCategorySpinner(subCategories: List<Children?>) {
        viewBinding.spinnerSubCategory.isEnabled = true // Enable subcategory spinner
        requireContext().setupSpinner(
            viewBinding.spinnerSubCategory,
            subCategories,
            itemSelected = { selectedCategory ->
                // Fetch properties only if a subcategory is selected
                if (selectedCategory != null) {
                    viewModel.getAllProperties(selectedCategory.id ?: 0)
                } else {
                    propertiesAdapter.submitList(emptyList()) // Clear properties if no subcategory
                }
            },
            itemToString = { it?.name ?: "" }
        )
    }

    private fun submitValues() {
        val selectedValues = mutableListOf<Pair<String, String>>()

        for (i in 0 until propertiesAdapter.itemCount) {
            val property = propertiesAdapter.getItemAt(i)
            val otherValue = propertiesAdapter.getOtherValueAt(i)

            // If there's an 'Other' input, add that value
            if (otherValue.isNotEmpty()) {
                selectedValues.add(property?.name!! to otherValue)
            } else {
                // If "Other" is not selected, get the last selected option
                selectedValues.add(property?.options?.firstOrNull()?.name!! to "")
            }
        }

        val list = mutableListOf<PairString>()
        selectedValues.forEach {
            list.add(PairString(fistValue = it.first, secondValue = it.second))
        }
        val action =
            CategoriesFragmentDirections.actionCategoriesFragmentToDetailsFragment(list.toTypedArray())
        findNavController().navigate(action)
    }

}