package com.foodApp.managementapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.foodApp.managementapp.MainActivity
import com.foodApp.managementapp.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                val myIntent = Intent(this@SplashScreen, MainActivity::class.java)
                this@SplashScreen.startActivity(myIntent)
                finish()
            }
        },3000)
    }

    }
