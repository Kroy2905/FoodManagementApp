package com.foodApp.managementapp.ui

import android.annotation.SuppressLint
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
import com.foodApp.managementapp.Utilities.Utils.Companion.PARTNER_CODE
import com.foodApp.managementapp.Utilities.Utils.Companion.RESTAURENT_CODE
import com.foodApp.managementapp.Utilities.Utils.Companion.SUCCESS
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivityLoginBinding
import com.foodApp.managementapp.databinding.ActivityMainBinding
import com.foodApp.managementapp.interfaces.OTPsignIninterface
import com.foodApp.managementapp.models.partnerReqBody
import com.foodApp.managementapp.models.restaurantReqBody
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
                if(loginCode.equals(RESTAURENT_CODE)){
                    val intent = Intent(this, RestaurantHomeScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                else if(loginCode.equals(PARTNER_CODE)){
                    val intent = Intent(this, PartnerHomeScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@LoginActivity,"Invalid Login ID",Toast.LENGTH_SHORT).show()
                }


                // Reset the LiveData value to avoid navigating multiple times
                viewModel.navigateToNextActivity()
            }
        })
    }

    var otp = false
    var requiredFields = true
    var edittextList = mutableListOf<EditText>()
    var loginCode = ""
    override fun setupViews() {
        viewModel.PTNRverification.observe(this) {
            Log.d("Response->", it.status)
            if (it.status.equals(SUCCESS)) {
                binding.otpEdittext.visibility = View.VISIBLE
                binding.loginButton.text = "LOGIN"
                otp = true
                //  viewModel.getOTP(binding.mobileEdittext.text.toString(),this)
            } else {
                Toast.makeText(
                    this,
                    "Invalid Partner ID/Mob No",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.RSNTverification.observe(this){
            Log.d("Response->",it.status)
            if(it.status.equals(SUCCESS)){
                binding.otpEdittext.visibility=View.VISIBLE
                binding.loginButton.text="LOGIN"
                otp = true
                //  viewModel.getOTP(binding.mobileEdittext.text.toString(),this)
            }
            else{
                Toast.makeText(this,"Invalid Restaurant ID/Mob No",Toast.LENGTH_SHORT).show()
            }
        }


        binding.loginButton.setOnClickListener {
            Log.d("OTPTEXT->","$otp")

            if (!otp) {
                edittextList.add(binding.IdEdittext)
                edittextList.add(binding.mobileEdittext)
                if(!viewModel.checkEditTextEmpty(edittextList)){
                    loginCode=binding.IdEdittext.text.toString().substring(0, minOf(binding.IdEdittext.length(), 4))
                    Log.d("LoginCode->",loginCode)
                    when (loginCode){
                        PARTNER_CODE->{
                            Log.d("LoginCode->","Entered PTNR")
//                            binding.otpEdittext.visibility=View.VISIBLE
//                                    binding.loginButton.text="LOGIN"
//                                    otp = true
//                                    viewModel.getOTP(binding.mobileEdittext.text.toString(),this)

                            viewModel.verifyPartner(partnerReqBody(binding.IdEdittext.text.toString(),binding.mobileEdittext.text.toString()))



                        }
                        RESTAURENT_CODE->{
                            Log.d("LoginCode->","Entered RSNT")


                            viewModel.verifyRestaurant(restaurantReqBody(binding.IdEdittext.text.toString(),binding.mobileEdittext.text.toString()))


                        }
                        else->{
                            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
                        }
                    }

                    //  Verification from backend to be done here
                    /**
                     * if backend verfies the id use below code
                     * else show a toast message invalid ID/Mob No.
                     */

                }


            }else{
                edittextList.clear()
                edittextList.add(binding.otpEdittext)
                if(!viewModel.checkEditTextEmpty(edittextList)){
                    Log.d("OTPTEXT->",binding.otpEdittext.text!!.trim().toString())
                 //  viewModel.verifyCode(binding.otpEdittext.text!!.trim().toString())
                }
            }
        }
    }



}