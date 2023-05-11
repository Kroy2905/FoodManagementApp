package com.foodApp.managementapp.ui.Partner.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.foodApp.managementapp.base.BaseFragment
import com.foodApp.managementapp.databinding.FragmentFooditemsBinding
import com.foodApp.managementapp.ui.Restaurant.Additems.AddItemViewModel

class ProfileFragment : BaseFragment<FragmentFooditemsBinding, AddItemViewModel>(
    AddItemViewModel::class.java,
    FragmentFooditemsBinding::inflate
) {



    override fun setupViews() {

    }
}