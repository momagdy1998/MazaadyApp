package com.example.mazaady.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mazaady.data.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {

    val courses = repo.getAllCoursesNames().asLiveData()
    val peoples = repo.getAllPeoples().asLiveData()
    val coursesDetails = repo.getAllCoursesDetails().asLiveData()



}