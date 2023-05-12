package com.foodApp.managementapp.models

data class addFoodBody(
    var foodDescription: String,
    var foodImgUrl: String,
    var foodPrice: Int,
    var foodTitle: String,
    var restaurantID: String
){
    companion object {
        private val instance = addFoodBody("","",
            0,
            "" ,"RSNT1683053142858"

        )
        fun getInstance(): addFoodBody {
            return instance
        }
    }
}
