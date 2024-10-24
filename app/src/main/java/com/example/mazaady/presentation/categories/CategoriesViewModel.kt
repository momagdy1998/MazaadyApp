package com.example.mazaady.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazaady.data.model.allCategoriesResponse.AllCategoriesResponse
import com.example.mazaady.data.model.allPropertiesResponse.AllPropertiesResponse
import com.example.mazaady.data.model.getAllOptions.GetOptionsResponse
import com.example.mazaady.data.repo.AppRepository
import com.example.mazaady.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {

    private val _allCategory =
        MutableStateFlow<Resources<AllCategoriesResponse?>>(Resources.Loading)
    val allCategory: StateFlow<Resources<AllCategoriesResponse?>> get() = _allCategory

    private val _allProperty =
        MutableStateFlow<Resources<AllPropertiesResponse?>>(Resources.Loading)
    val allProperty: StateFlow<Resources<AllPropertiesResponse?>> get() = _allProperty

    private val _allChildOptions =
        MutableStateFlow<Resources<GetOptionsResponse?>>(Resources.Loading)
    val allChildOptions: StateFlow<Resources<GetOptionsResponse?>> get() = _allChildOptions


    fun getAllCategories() {
        viewModelScope.launch {
            repo.getAllCategories().collect { result ->
                _allCategory.emit(result)
            }


        }
    }

    fun getAllProperties(id: Int) {
        viewModelScope.launch {
            repo.getAllProperties(id).collect { result ->
                _allProperty.emit(result)
            }
        }
    }

    fun getChildOptions(optionId: Int) {
        viewModelScope.launch {
            repo.getChildOptions(optionId).collect { result ->
                _allChildOptions.emit(result)
            }
        }
    }

}
