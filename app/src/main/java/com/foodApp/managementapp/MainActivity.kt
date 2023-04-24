package com.foodApp.managementapp

import android.os.Bundle
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.base.BaseViewModelFactory
import com.foodApp.managementapp.databinding.ActivityMainBinding
import com.foodApp.managementapp.viewmodels.MainViewModel
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

class MainActivity :BaseActivity<ActivityMainBinding, MainViewModel>(
    { inflater -> ActivityMainBinding.inflate(inflater) },
    MainViewModel::class.java,

) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}