package com.foodApp.managementapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

abstract class BaseActivity<B : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater) -> B,
    private val viewModelClass: Class<VM>,
    private val viewModelFactory: ViewModelProvider.Factory
) : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }
}

