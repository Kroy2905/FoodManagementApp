package com.foodApp.managementapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.foodApp.managementapp.MainActivity
import com.foodApp.managementapp.R
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivityLoginBinding
import com.foodApp.managementapp.databinding.ActivityMainBinding
import com.foodApp.managementapp.interfaces.OTPsignIninterface
import com.foodApp.managementapp.viewmodels.LoginViewModel
import com.foodApp.managementapp.viewmodels.MainViewModel

class LoginActivity :  BaseActivity<ActivityLoginBinding, LoginViewModel>(
    LoginViewModel::class.java,
    { inflater -> ActivityLoginBinding.inflate(inflater) },
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigateToNextActivity.observe(this, Observer { navigateToNextActivity ->
            if (navigateToNextActivity) {
                // Navigate to the next activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Reset the LiveData value to avoid navigating multiple times
                viewModel.navigateToNextActivity()
            }
        })
    }

    var otp = false
    var requiredFields = true
    var edittextList = mutableListOf<EditText>()
    override fun setupViews() {
        binding.loginButton.setOnClickListener {
            Log.d("OTPTEXT->","$otp")

            if (!otp) {
                edittextList.add(binding.IdEdittext)
                edittextList.add(binding.mobileEdittext)
                if(!viewModel.checkEditTextEmpty(edittextList)){
                    binding.otpEdittext.visibility=View.VISIBLE
                    binding.loginButton.text="LOGIN"
                    otp = true
                    viewModel.getOTP(binding.mobileEdittext.text.toString(),this)
                }


            }else{
                edittextList.clear()
                edittextList.add(binding.otpEdittext)
                if(!viewModel.checkEditTextEmpty(edittextList)){
                    Log.d("OTPTEXT->",binding.otpEdittext.text!!.trim().toString())
                   viewModel.verifyCode(binding.otpEdittext.text!!.trim().toString())
                }
            }
        }
    }



}