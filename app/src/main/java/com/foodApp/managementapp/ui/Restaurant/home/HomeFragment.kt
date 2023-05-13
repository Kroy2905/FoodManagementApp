package com.foodApp.managementapp.ui.Restaurant.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.foodApp.managementapp.base.BaseFragment
import com.foodApp.managementapp.databinding.FragmentAddItemBinding
import com.foodApp.managementapp.databinding.FragmentHomeBinding
import com.foodApp.managementapp.ui.Restaurant.Additems.AddItemViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    HomeViewModel::class.java,
    FragmentHomeBinding::inflate
) {
    override fun setupViews() {
       viewModel.showProgressBar()
    }


}