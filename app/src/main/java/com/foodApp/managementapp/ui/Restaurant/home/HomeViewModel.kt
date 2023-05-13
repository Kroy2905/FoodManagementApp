package com.foodApp.managementapp.ui.Restaurant.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel

class HomeViewModel (private val repository: BaseRepository):BaseViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "Details of restautant will be displayed"
    }
    val text: LiveData<String> = _text
}