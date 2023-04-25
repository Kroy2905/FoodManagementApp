package com.foodApp.managementapp

import android.os.Bundle
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivityMainBinding

import com.foodApp.managementapp.viewmodels.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class.java,
    { inflater -> ActivityMainBinding.inflate(inflater) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.demoData.observe(this) {
                binding.textview.text=it.toString()
        }


    }

    override fun setupViews() {

        // Initialize any views here
    }
}
