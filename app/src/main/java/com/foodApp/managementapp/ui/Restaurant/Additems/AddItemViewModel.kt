package com.foodApp.managementapp.ui.Restaurant.Additems

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.foodApp.managementapp.models.addFoodBody
import com.foodApp.managementapp.models.addFoodReqBody
import com.foodApp.managementapp.models.statusResponse
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class AddItemViewModel  (private val repository: BaseRepository):BaseViewModel(){


    private var _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri>
        get() = _imageUri

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    private var _imageURL = MutableLiveData<String?>()
    val imageURL: LiveData<String?>
        get() = _imageURL
    fun uploadImage(imageUri: Uri,filename :String) {
        val storage = FirebaseStorage.getInstance()
// Get a reference to the Firebase Storage root directory
        val storageRef = storage.reference

        // Get a reference to the image file in Firebase Storage
        val imageRef = storageRef.child("fooditems/$filename")

        // Upload the image file to Firebase Storage
        imageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                imageRef.downloadUrl
                    .addOnSuccessListener { uri ->
                        val httpsUrl = uri.toString()
                       // addFoodBody.getInstance().foodImgUrl=httpsUrl
                        _imageURL.postValue(httpsUrl)
                        // Do something with the HTTPS URL
                        Log.d("Check->", "HTTPS URL: $httpsUrl")
                    }
                    .addOnFailureListener { exception ->
                        // Handle the error
                        _imageURL.postValue(null)
                        Log.e("Check->", "Failed to get HTTPS URL: ${exception.message}")
                    }

            }
            .addOnFailureListener {
                _imageURL.postValue(null)

            }



}
    fun addFoodItem(addFoodReqBody: addFoodReqBody){
        viewModelScope.launch {
            repository.addFooditem(addFoodReqBody)
        }
    }
    val  addFoodItem : LiveData<statusResponse>
        get() = repository._addfoodItem



}