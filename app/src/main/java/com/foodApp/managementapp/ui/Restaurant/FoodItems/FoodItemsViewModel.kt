package com.foodApp.managementapp.ui.Restaurant.FoodItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodItemsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Restaurant can add items"
    }
    val text: LiveData<String> = _text
}