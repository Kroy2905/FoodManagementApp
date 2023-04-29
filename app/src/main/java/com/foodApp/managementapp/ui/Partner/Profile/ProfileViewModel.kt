package com.foodApp.managementapp.ui.Partner.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Delivery Partner Info"
    }
    val text: LiveData<String> = _text
}