package com.foodApp.managementapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.foodApp.managementapp.MainActivity
import com.foodApp.managementapp.R
import com.foodApp.managementapp.Utilities.Utils
import com.foodApp.managementapp.Utilities.Utils.Companion.isLoggedIn
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivitySplashScreenBinding
import com.foodApp.managementapp.viewmodels.MainViewModel

class SplashScreen :BaseActivity<ActivitySplashScreenBinding, MainViewModel>(
MainViewModel::class.java,
{ inflater -> ActivitySplashScreenBinding.inflate(inflater) }
){
    override fun setupViews() {

        Handler(Looper.getMainLooper()).postDelayed({
            val isLoggedIn = viewModel.isLoggedIn(this@SplashScreen)
            val userId = viewModel.getUserId(this@SplashScreen)

            if (isLoggedIn && userId != null) {
                val loginCode = userId.substring(0, minOf(userId.length, 4))

                if(loginCode.equals(Utils.RESTAURENT_CODE)){
                    val intent = Intent(this, RestaurantHomeScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                else if(loginCode.equals(Utils.PARTNER_CODE)){
                    val intent = Intent(this, PartnerHomeScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Log.d("data","invalid data")
                }
            } else {
                val myIntent = Intent(this@SplashScreen, LoginActivity::class.java)
                this@SplashScreen.startActivity(myIntent)
                finish()
                // User is not logged in, show login screen
            }



        },4000)
    }

}




