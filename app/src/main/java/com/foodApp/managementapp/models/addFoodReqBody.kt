package com.foodApp.managementapp.models

data class addFoodReqBody(
    val foodDescription: String,
    val foodImgUrl: String,
    val foodPrice: Int,
    val foodTitle: String,
    val restaurantID: String
)