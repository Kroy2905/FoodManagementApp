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
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.lang.reflect.Array.get
import java.util.*

class AddItemFragment : BaseFragment<FragmentAddItemBinding, AddItemViewModel>(
    AddItemViewModel::class.java,
   FragmentAddItemBinding::inflate
){
     private var imagepath:String? = null
     private var imageURI:Uri? = null
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

            uri?.let { viewModel.setImageUri(it) }
        imageURI = uri
            //imagepath=viewModel.getPathFromUri(requireContext(),uri!!)


    }

    override fun setupViews() {
            binding.uploadImagebn.setOnClickListener {
               getImage.launch("image/*")
              //  pickImage()
            }
        binding.addItembtn.setOnClickListener {
                if(imageURI!=null){
                    viewModel.uploadImage(imageURI!!,requireContext())
                }

        }

        viewModel.imageUri.observe(this) { uri ->

            Log.d("URI IMAGE->",uri.toString())
            binding.foodimg.setImageURI(uri)
        }



        viewModel.imageURL.observe(this) { url->

            Log.d("URI IMAGE->","URL of image $url")

        }
    }

    val   REQUEST_CODE_PICK_IMAGE = 100


    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE && data != null) {
            val selectedImageUri: Uri = data.data!!
            binding.foodimg.setImageURI(selectedImageUri)

            val filePath = getPathFromUri(selectedImageUri)
            uploadImageToFirestore(filePath)
        }
    }

    private fun getPathFromUri(uri: Uri): String {
        var filePath = ""
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            filePath = it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
            it.close()
        }
        return filePath
    }

    private fun uploadImageToFirestore(filePath: String) {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")
        val uploadTask = imageRef.putFile(Uri.fromFile(File(filePath)))

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                Log.d(TAG, "Image URL: $imageUrl")
            }
        }.addOnFailureListener {
            Log.e(TAG, "Error uploading image", it)
        }
    }





}