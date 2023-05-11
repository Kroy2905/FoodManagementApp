package com.foodApp.managementapp.ui.Restaurant.Orders
import com.bumptech.glide.Glide
import com.foodApp.managementapp.base.BaseFragment

import com.foodApp.managementapp.databinding.FragmentOrderBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(
    OrderViewModel::class.java,
    FragmentOrderBinding::inflate
) {


    override fun setupViews() {
//        val storage = FirebaseStorage.getInstance()
//        val storageRef = storage.reference.child("fooditems/foodItem1")
//        Glide.with(this /* context */)
//            .load(storageRef)
//            .into(binding.textGallery)
        // we will get the default FirebaseDatabase instance
        val storageRef = Firebase.storage.reference
        Glide.with(this /* context */)
            //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
            .load("https://firebasestorage.googleapis.com/v0/b/grabbersmanager.appspot.com/o/fooditems%2FfoodItem1?alt=media&token=216b7a19-76ba-440c-9548-2de17c1784df")
            .into(binding.textGallery)
        


    }


}

