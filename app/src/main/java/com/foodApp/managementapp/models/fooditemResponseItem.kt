package com.foodApp.managementapp.models

data class fooditemResponseItem(
    val foodCategory: String,
    val foodDescription: String,
    val foodID: String,
    val foodImgUrl: Any,
    val foodPrice: Int,
    val foodTitle: String,
    val id: Int,
    val restaurantID: String
)