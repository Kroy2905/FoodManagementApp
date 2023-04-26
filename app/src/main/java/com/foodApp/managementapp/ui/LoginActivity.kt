package com.foodApp.managementapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodApp.managementapp.R
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivityLoginBinding
import com.foodApp.managementapp.databinding.ActivityMainBinding
import com.foodApp.managementapp.viewmodels.MainViewModel

class LoginActivity :  BaseActivity<ActivityLoginBinding, MainViewModel>(
    MainViewModel::class.java,
    { inflater -> ActivityLoginBinding.inflate(inflater) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {

    }
}