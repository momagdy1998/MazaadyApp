package com.example.mazaady.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<DB : ViewBinding> : Fragment() {

    protected lateinit var viewBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateBinding(layoutInflater, container)
        return viewBinding.root


    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): DB
}