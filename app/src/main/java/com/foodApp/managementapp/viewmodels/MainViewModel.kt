package com.foodApp.managementapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.foodApp.managementapp.models.demoResponse
import kotlinx.coroutines.launch

class MainViewModel (private val repository: BaseRepository):BaseViewModel(){

    init {
        viewModelScope.launch {
            repository.getDemodata()

        }

    }
    val  demoData : LiveData<demoResponse>
        get() = repository.demoData

}