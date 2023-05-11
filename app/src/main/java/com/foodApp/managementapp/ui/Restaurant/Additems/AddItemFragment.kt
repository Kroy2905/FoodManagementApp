package com.foodApp.managementapp.ui.Restaurant.Additems

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodApp.managementapp.R
import com.foodApp.managementapp.base.BaseFragment
import com.foodApp.managementapp.databinding.FragmentAddItemBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.lang.reflect.Array.get
import java.util.*

class AddItemFragment : BaseFragment<FragmentAddItemBinding, AddItemViewModel>(
    AddItemViewModel::class.java,
   FragmentAddItemBinding::inflate
) {
    private var imageURI: Uri? = null
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

        uri?.let { viewModel.setImageUri(it) }
        imageURI = uri!!


    }
    var ct=0;

    override fun setupViews() {
        binding.uploadImagebn.setOnClickListener {
            //  getImage.launch("image/*")

            // Create an intent to open the gallery
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )


        }
        binding.addItembtn.setOnClickListener {
            ct++
            if (imageURI != null) {
                viewModel.uploadImage(imageURI!!,"foodItem${ct.toString()}")
            }

        }


    }

    // Handle the result of the image selection
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            binding.foodimg.setImageURI(imageUri)
            imageURI = imageUri!!
        }
    }
}