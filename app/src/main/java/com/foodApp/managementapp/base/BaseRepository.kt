package com.foodApp.managementapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodApp.managementapp.APIservice
import com.foodApp.managementapp.models.demoResponse
import com.foodApp.managementapp.models.partnerReqBody
import com.foodApp.managementapp.models.restaurantReqBody
import com.foodApp.managementapp.models.statusResponse

class BaseRepository  (private  val  apIservice: APIservice) {

    private val demoLiveData = MutableLiveData<demoResponse>()
    private val partnerVerify =MutableLiveData<statusResponse>()
    private val restaurantverify =MutableLiveData<statusResponse>()
//    private val faceEmbeddings =MutableLiveData<faceEmbeddings>()
//    val driverlogsLogResponse =MutableLiveData<LogResponse>()
//
    val demoData: LiveData<demoResponse>
        get() = demoLiveData
        // Partner live data
    val partnerResponse: LiveData<statusResponse>
        get() = partnerVerify

//    Restaurant Live Data
    val restauarantResponse: LiveData<statusResponse>
        get() = restaurantverify
//    val customerAddedLogResponse : LiveData<LogResponse>
//    get() = customerAdded
//    val getTripDetails : LiveData<tripResponse>
//    get() = tripdetails
//
//    val fembs : LiveData<faceEmbeddings>
//    get() = faceEmbeddings
//    val driverlogs : LiveData<LogResponse>
//        get() = driverlogsLogResponse
//
//
////    suspend fun getQuotes(page: Int) {
////        val result = apIservice.getQuotes(page)
////        if (result?.body() != null){
////            quotesLiveData.postValue(result.body())
////        }
////
////    }
//
//
    suspend fun getDemodata() {
        val result = apIservice.demofunc()
        if (result.body()!=null){
            demoLiveData.postValue(result.body())
        }

    }

    suspend fun verifyRestaurant(restaurantReqBody: restaurantReqBody) {
        val result = apIservice.verifyRestaurant(restaurantReqBody)
        if (result.body()!=null){
           restaurantverify.postValue(result.body())
        }

    }

    suspend fun verifyPartner(partnerReqBody: partnerReqBody) {
        val result = apIservice.verifyPartner(partnerReqBody)
        if (result.body()!=null){
            Log.d("Response->",result.body().toString())
            partnerVerify.postValue(result.body())
        }

    }
//    suspend fun getTripDetails(registration_no: String) {
//        val result = apIservice.getTripDetails(registration_no)
//        if (result.body() != null) {
//            tripdetails.postValue(result.body())
//        }
//    }
//        suspend fun getFaceEmbeddings(driverID1: String,driverID2:String,driverID3: String) {
//            val result = apIservice.getFaceEmbs(driverID1,driverID2,driverID3)
//            if (result.body()!=null){
//                faceEmbeddings.postValue(result.body())
//            }
//
//    }
//    suspend fun sendDriverLogs(logDetails: LogDetails): Response<LogResponse> {
//        val result = apIservice.createEvent(logDetails)
//        if (result.body()!=null){
//            driverlogsLogResponse.postValue(result.body())
//        }
//        return  result
//    }


}