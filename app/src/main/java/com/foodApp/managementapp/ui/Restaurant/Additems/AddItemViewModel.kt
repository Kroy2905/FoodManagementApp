package com.foodApp.managementapp.ui.Restaurant.Additems

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.google.firebase.firestore.FirebaseFirestore

import java.io.ByteArrayOutputStream
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

    fun getPathFromUri(context: Context, uri: Uri): String {
        var filePath = ""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            filePath = it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
            it.close()
        }
        return filePath
    }

    private val db = FirebaseFirestore.getInstance()

    fun uploadImage(uri: Uri,context: Context) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        var quality = 75
        while (outputStream.toByteArray().size > 1024 * 1024) {
            outputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            quality -= 5
        }
        val byteArray = outputStream.toByteArray()
        // Convert the ByteArray to a Base64 encoded String
        val base64EncodedString = Base64.encodeToString(byteArray, Base64.DEFAULT)

// Create a data object to store in Firestore
        val data = hashMapOf("image" to base64EncodedString)
        val storageRef = db.collection("images").document()
        storageRef.set(byteArray)
            .addOnSuccessListener {
                // Handle success
            }
            .addOnFailureListener {
                // Handle failure
            }
    }



//
//    // Get a reference to the Firestore collection
//    private val db = Firebase.firestore
//    private val collectionRef = db.collection("images")
//
//    // Get a reference to the storage location
//    private val storageRef = Firebase.storage.reference.child("images")
//
//    fun uploadImage(imagePath: String) {
//        Log.d("URI IMAGE->",imagePath)
//
//        viewModelScope.launch {
//            try {
//
//                // Create a unique filename for the image
//                val filename = UUID.randomUUID().toString()
//
//                // Get a reference to the image file
//                val imageRef = storageRef.child("$filename.jpg")
//
//                // Convert the image to a byte array
//                val bitmap = BitmapFactory.decodeFile(imagePath)
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val data = baos.toByteArray()
//
//                // Upload the image to the storage location
//                val uploadTask = imageRef.putBytes(data)
//
//                // Add a listener to the upload task to monitor the progress of the upload
//                uploadTask.addOnSuccessListener {
//                    Log.d("URI IMAGE->","Entered 1")
//                    // Get the download URL of the image
//                    imageRef.downloadUrl.addOnSuccessListener { uri ->
//                        // Save the download URL to Firestore
//                        val image = hashMapOf("url" to uri.toString())
//                        collectionRef.add(image)
//                        Log.d("URI IMAGE->","Entered 2 ")
//                    }
//                }.addOnFailureListener { exception ->
//                    // Handle any errors
//                }
//            } catch (e: Exception) {
//                // Handle any exceptions
//            }
//        }
//    }
//


//    fun uploadImage(imageUri: Uri) {
//        Log.d("URI IMAGE->",imageUri.toString())
//        val uploadUri = Uri.fromFile(File(imageUri.toString()))
//
//        val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.png")
//        storageRef.putFile(uploadUri)
//            .addOnSuccessListener {
//                Log.d("URI IMAGE->","Entered 1 ")
//
//
//                // Image uploaded successfully
//                // Get the download URL of the image
//                storageRef.downloadUrl.addOnSuccessListener { uri ->
//                    // Image download URL received
//                    // Save the URL to Firestore
//                    val firestore = FirebaseFirestore.getInstance()
//                    val image = hashMapOf(
//                        "url" to uri.toString()
//                    )
//                    firestore.collection("FoodItems").add(image)
//                        .addOnSuccessListener {
//                            Log.d("URI IMAGE->","Entered 2 ")
//                            _imageURL.value=it.path.toString()
//
//                            // Image URL saved to Firestore
//                        }
//                        .addOnFailureListener { e ->
//                            Log.d("URI IMAGE->","Entered 3 ")
//                            _imageURL.value=null
//                            // Error saving image URL to Firestore
//                        }
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.d("URI IMAGE->","Entered 4 ")
//                _imageURL.value=null
//                // Error uploading image to Firebase Storage
//            }
//    }



}