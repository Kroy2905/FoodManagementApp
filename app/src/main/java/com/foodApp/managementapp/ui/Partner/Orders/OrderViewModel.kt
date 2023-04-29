package com.foodApp.managementapp.ui.Partner.Orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Orders to be delivered "
    }
    val text: LiveData<String> = _text
}