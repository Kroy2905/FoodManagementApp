package com.foodApp.managementapp

//import com.myelin.ics.ui.models.*
import com.foodApp.managementapp.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIservice {
    @GET("/prd")    // API to be written here
     suspend fun demofunc() : Response <demoResponse>

//
//
//    @POST("/addcustomerdetails")    // API to add customer
//    suspend fun  addCustomer(@Body customerDetails: customerDetails) : Response <LogResponse>
//
//    // GET trip details
//    @GET("/tripdetails")    // API to be written here
//    suspend fun getTripDetails(@Query("Veh_Reg_No") registration_no:String) : Response <tripResponse>
//
//
//    @GET("/getdriverfe")    // API to be written here
//    suspend fun getFaceEmbs(@Query("DriverID1") DriverID1:String,
//                            @Query("DriverID2") DriverID2:String,
//                            @Query("DriverID3") DriverID3:String
//    ) : Response <faceEmbeddings>
//
    @POST("/restaurantverify")    // API to verify restaurant
    suspend fun  verifyRestaurant(@Body restaurantReqBody: restaurantReqBody) : Response <statusResponse>


    @POST("/partnerverify")    // API to verify restaurant
    suspend fun  verifyPartner(@Body partnerReqBody: partnerReqBody) : Response <statusResponse>

    @POST("/addfood")    // API to verify restaurant
    suspend fun  addfoodItem(@Body addFoodReqBody: addFoodReqBody) : Response <statusResponse>

    // GET trip details
    @GET("/getfoods")    // API to be written here
    suspend fun getFood(@Query("restaurantID") restaurantID:String) : Response <fooditemResponse>

}
