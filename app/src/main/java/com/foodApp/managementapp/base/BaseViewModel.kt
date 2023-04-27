package com.foodApp.managementapp.base

import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel

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



}