package com.foodApp.managementapp.ui.Restaurant.FoodItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.foodApp.managementapp.models.addFoodReqBody
import com.foodApp.managementapp.models.fooditemResponse
import com.foodApp.managementapp.models.statusResponse
import kotlinx.coroutines.launch

class FoodItemsViewModel  (private val repository: BaseRepository):BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Restaurant can add items"
    }
    val text: LiveData<String> = _text

    fun getFoodItems(restaurantId:String){
        viewModelScope.launch {
            repository.getFooditems(restaurantId)
        }
    }
    val  getfoodItems : LiveData<fooditemResponse>
        get() = repository._getfooditem
}