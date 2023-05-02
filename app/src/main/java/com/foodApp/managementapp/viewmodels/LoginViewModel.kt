package com.foodApp.managementapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.foodApp.managementapp.interfaces.OTPsignIninterface
import com.foodApp.managementapp.models.demoResponse
import com.foodApp.managementapp.models.partnerReqBody
import com.foodApp.managementapp.models.restaurantReqBody
import com.foodApp.managementapp.models.statusResponse
import com.foodApp.managementapp.ui.LoginActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class LoginViewModel(private val repository: BaseRepository): BaseViewModel(){


    // variable for FirebaseAuth class
    private val _navigateToNextActivity = MutableLiveData<Boolean>()
    val navigateToNextActivity: LiveData<Boolean>
        get() = _navigateToNextActivity

    private var mAuth: FirebaseAuth? = null

    // string for storing our verification ID
    private var verificationId: String? = null

    fun getOTP( mob_no:String,activity: LoginActivity){
        mAuth = FirebaseAuth.getInstance();
        // send OTP method for getting OTP from Firebase.
        // send OTP method for getting OTP from Firebase.
        val phone = "+91$mob_no"
        sendVerificationCode(phone,activity)

    }
    fun navigateToNextActivity() {
        // Do some work here...
        // Once the work is done, set the LiveData value to notify the activity
        _navigateToNextActivity.postValue(false)
    }

    fun signInWithCredential(credential: PhoneAuthCredential){
        // inside this method we are checking if
        // the code entered is correct or not.

        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // if the code is correct and the task is successful
                    // we are sending our user to new activity.
                    _navigateToNextActivity.value = true


//                    val i = Intent(this@MainActivity, HomeActivity::class.java)
//                    startActivity(i)
//                    finish()
                } else {

                    // if the code is not correct then we are
                    // displaying an error message to the user.
//                    Toast.makeText(
//                        this@MainActivity,
//                        task.exception.getMessage(),
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
                }
            }

    }

    private fun sendVerificationCode(number: String,activity: LoginActivity) {
        // this method is used for getting
        // OTP on user phone number.
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // callback method is called on Phone auth provider.
    // initializing our callbacks for on
    // verification callback method.
    private val mCallBack: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            // below method is used when
            // OTP is sent from Firebase
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                // when we receive the OTP it
                // contains a unique id which
                // we are storing in our string
                // which we have already created.
                verificationId = s
            }

            // this method is called when user
            // receive OTP from Firebase.
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                // below line is used for getting OTP code
                // which is sent in phone auth credentials.
                val code = phoneAuthCredential.smsCode

                // checking if the code
                // is null or not.
                if (code != null) {
                    // if the code is not null then
                    // we are setting that code to
                    // our OTP edittext field.
                   // edtOTP.setText(code)

                    // after setting this code
                    // to OTP edittext field we
                    // are calling our verifycode method.
                    //verifyCode(code)
                }
            }

            // this method is called when firebase doesn't
            // sends our OTP code due to any error or issue.
            override fun onVerificationFailed(e: FirebaseException) {
                // displaying error message with firebase exception.
              //  Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    // below method is use to verify code from Firebase.
    fun verifyCode(code: String){
        // below line is used for getting
        // credentials from our verification id and code.
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential)
    }



    fun verifyRestaurant(restaurantReqBody: restaurantReqBody){
        viewModelScope.launch {
            repository.verifyRestaurant(restaurantReqBody)

        }
    }
    val  RSNTverification : LiveData<statusResponse>
        get() = repository.restauarantResponse



    fun verifyPartner(partnerReqBody: partnerReqBody){
        viewModelScope.launch {
            repository.verifyPartner(partnerReqBody)

        }
    }
    val  PTNRverification : LiveData<statusResponse>
    get() = repository.partnerResponse


}