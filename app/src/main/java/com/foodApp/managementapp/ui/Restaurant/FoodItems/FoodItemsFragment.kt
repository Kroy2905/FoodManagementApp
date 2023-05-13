package com.foodApp.managementapp.ui.Restaurant.FoodItems

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.foodApp.managementapp.adapters.fooditemadapter
import com.foodApp.managementapp.base.BaseFragment
import com.foodApp.managementapp.databinding.FragmentAddItemBinding
import com.foodApp.managementapp.databinding.FragmentFooditemsBinding
import com.foodApp.managementapp.models.addFoodBody

import com.foodApp.managementapp.ui.Restaurant.Additems.AddItemViewModel


class FoodItemsFragment : BaseFragment<FragmentFooditemsBinding, FoodItemsViewModel>(
    FoodItemsViewModel::class.java,
    FragmentFooditemsBinding::inflate
) {



    override fun setupViews() {
        viewModel.getfoodItems.observe(this){
            if(it.size>0){
                Log.d("Check->","Entered adapter initialisation")
                val fooditemadaper = fooditemadapter(it,requireContext())
             //   binding.foodItemListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.foodItemListRecyclerview.adapter = fooditemadaper
            }
        }




        viewModel.getFoodItems(addFoodBody.getInstance().restaurantID)

    }
}