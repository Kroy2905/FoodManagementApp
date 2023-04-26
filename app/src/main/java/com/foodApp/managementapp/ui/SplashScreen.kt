package com.foodApp.managementapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.foodApp.managementapp.MainActivity
import com.foodApp.managementapp.R
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivitySplashScreenBinding
import com.foodApp.managementapp.viewmodels.MainViewModel

class SplashScreen :BaseActivity<ActivitySplashScreenBinding, MainViewModel>(
MainViewModel::class.java,
{ inflater -> ActivitySplashScreenBinding.inflate(inflater) }
){
    override fun setupViews() {

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                val myIntent = Intent(this@SplashScreen, LoginActivity::class.java)
                this@SplashScreen.startActivity(myIntent)
                finish()
            }
        },3000)
    }

}




