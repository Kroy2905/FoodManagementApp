package com.foodApp.managementapp.base

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.foodApp.managementapp.Utilities.Utils
import com.foodApp.managementapp.Utilities.Utils.Companion.PREFS_NAME
import com.foodApp.managementapp.Utilities.Utils.Companion.UserId
import com.foodApp.managementapp.Utilities.Utils.Companion.isLoggedIn

abstract class BaseViewModel : ViewModel() {
  var requiredFieldsEmpty = false
    fun checkEditTextEmpty(editTexts: List<EditText>):Boolean {
        requiredFieldsEmpty=false
        for (editText in editTexts) {

            if (editText.text.toString().trim().isEmpty()) {
                val errorMessage = "Error: ${editText.hint} cannot be empty"
                editText.error=errorMessage
                requiredFieldsEmpty= true
               // Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        return  requiredFieldsEmpty
    }


  fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(isLoggedIn, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean, userId: String,context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(Utils.isLoggedIn, isLoggedIn)
        editor.putString(UserId, userId)
        editor.apply()
    }

    fun getUserId(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(UserId, null)
    }

 fun clearLoggedIn(context: Context){
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }









}