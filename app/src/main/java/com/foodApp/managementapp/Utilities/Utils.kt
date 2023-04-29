package com.foodApp.managementapp.Utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {


    companion object{
        val CODE_PERM_CAMERA = 6112
        val REQUEST_ID_MULTIPLE_PERMISSIONS = 2
        //val BASE_URL = "https://quotable.io/"
        val BASE_URL = "https://k4474mxtk4.execute-api.ap-south-1.amazonaws.com/"
        val ONGOING_NOTIFICATION_ID = 6660
        val SUCCESS = "200 OK"
        val PARTNER_CODE = "PTNR"
        val RESTAURENT_CODE = "RSNT"
        var TRIP_START = false
        var WORKER_TAG = "ICSWorker"

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun getCurrentTime(): String {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return currentDateTime.format(formatter)
            // returning current time
        }
    }

}