package com.foodApp.managementapp.ui.Restaurant.Additems

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodApp.managementapp.base.BaseRepository
import com.foodApp.managementapp.base.BaseViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
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

//    // UploadImage method
//    private fun uploadImage() {
//        if (filePath != null) {
//
//            // Code for showing progressDialog while uploading
//            val progressDialog = ProgressDialog(this)
//            progressDialog.setTitle("Uploading...")
//            progressDialog.show()
//
//            // Defining the child of storageReference
//            val ref: StorageReference = storageReference
//                .child(
//                    "images/"
//                            + UUID.randomUUID().toString()
//                )
//
//            // adding listeners on upload
//            // or failure of image
//            ref.putFile(filePath)
//                .addOnSuccessListener { // Image uploaded successfully
//                    // Dismiss dialog
//                    progressDialog.dismiss()
//                    Toast
//                        .makeText(
//                            this@MainActivity,
//                            "Image Uploaded!!",
//                            Toast.LENGTH_SHORT
//                        )
//                        .show()
//                }
//                .addOnFailureListener { e -> // Error, Image not uploaded
//                    progressDialog.dismiss()
//                    Toast
//                        .makeText(
//                            this@MainActivity,
//                            "Failed " + e.message,
//                            Toast.LENGTH_SHORT
//                        )
//                        .show()
//                }
//                .addOnProgressListener(
//                    object : OnProgressListener<UploadTask.TaskSnapshot?>() {
//                        // Progress Listener for loading
//                        // percentage on the dialog box
//                        fun onProgress(
//                            taskSnapshot: UploadTask.TaskSnapshot
//                        ) {
//                            val progress = ((100.0
//                                    * taskSnapshot.bytesTransferred
//                                    / taskSnapshot.totalByteCount))
//                            progressDialog.setMessage(
//                                ("Uploaded "
//                                        + progress.toInt() + "%")
//                            )
//                        }
//                    })
//        }
//    }

//    private val db = FirebaseFirestore.getInstance()
//
//    fun uploadImage(uri: Uri,context: Context) {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        val bitmap = BitmapFactory.decodeStream(inputStream)
//        val outputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
//        var quality = 75
//        while (outputStream.toByteArray().size > 1024 * 1024) {
//            outputStream.reset()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
//            quality -= 5
//        }
//        val byteArray = outputStream.toByteArray()
//        // Convert the ByteArray to a Base64 encoded String
//        val base64EncodedString = Base64.encodeToString(byteArray, Base64.DEFAULT)
//
//// Create a data object to store in Firestore
//        val data = hashMapOf("image" to base64EncodedString)
//        val storageRef = db.collection("images").document()
//        storageRef.set(byteArray)
//            .addOnSuccessListener {
//                // Handle success
//            }
//            .addOnFailureListener {
//                // Handle failure
//            }
//    }



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


    fun uploadImage(imageUri: Uri,filename :String) {
        val storage = FirebaseStorage.getInstance()
// Get a reference to the Firebase Storage root directory
        val storageRef = storage.reference

        // Get a reference to the image file in Firebase Storage
        val imageRef = storageRef.child("fooditems/$filename")

        // Upload the image file to Firebase Storage
        imageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                Log.d("Check->",it.storage.downloadUrl.toString())

                //gs://grabbersmanager.appspot.com/images

                // Image upload successful
                // Do something here, like displaying a success message or updating a database
            }
            .addOnFailureListener {
                // Image upload failed
                // Handle the exception here, like displaying an error message or logging the error
            }



}



}