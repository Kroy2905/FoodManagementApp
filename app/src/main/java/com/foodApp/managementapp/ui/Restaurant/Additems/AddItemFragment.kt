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
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.foodApp.managementapp.R
import com.foodApp.managementapp.Utilities.Utils.Companion.CATEGORY_LIST
import com.foodApp.managementapp.Utilities.Utils.Companion.SUCCESS
import com.foodApp.managementapp.base.BaseFragment
import com.foodApp.managementapp.databinding.FragmentAddItemBinding
import com.foodApp.managementapp.models.addFoodBody
import com.foodApp.managementapp.models.addFoodReqBody
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
    var ct=0;
    private  lateinit var msgIdAdapter: ArrayAdapter<String>
    private var food_category:String? = null
    var edittextList = mutableListOf<EditText>()

    override fun setupViews() {

        viewModel.addFoodItem.observe(this){
            if(it.status == SUCCESS){
                Log.d("hitornot->","yes!!")
                Toast.makeText(requireContext(),"Item Added!",Toast.LENGTH_SHORT).show()
              //  findNavController().popBackStack()
                findNavController().navigate(R.id.action_nav_additem_to_nav_home)


               // requireActivity().supportFragmentManager.popBackStack()
            }else{
                Toast.makeText(requireContext(),"Item couldn't be added.Please try again!",Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.imageURL.observe(this) {
            addFoodBody.getInstance().foodPrice=Integer.parseInt(binding.etPrize.text.toString())
            addFoodBody.getInstance().foodDescription=binding.etDescription.text.toString()
            addFoodBody.getInstance().foodTitle=binding.etName.text.toString()

            //pushing to API
            viewModel.addFoodItem(addFoodReqBody(foodDescription = addFoodBody.getInstance().foodDescription,
             foodImgUrl= addFoodBody.getInstance().foodImgUrl,
                foodPrice = addFoodBody.getInstance().foodPrice,
                foodCategory=addFoodBody.getInstance().foodCategory,
                foodTitle = addFoodBody.getInstance().foodTitle,
                restaurantID = addFoodBody.getInstance().restaurantID,

            ))

        }

        msgIdAdapter = ArrayAdapter<String>(requireContext(),R.layout.msg_listitem,CATEGORY_LIST)
        binding.dropdownTextview.setAdapter(msgIdAdapter)
        binding.dropdownTextview.setOnItemClickListener { parent, view, position, id ->
            food_category = parent.getItemAtPosition(position).toString()
            addFoodBody.getInstance().foodCategory=food_category!!
            Toast.makeText(requireContext()," $food_category selected!!", Toast.LENGTH_SHORT).show()
        }

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
            edittextList.add(binding.etPrize)
            edittextList.add(binding.etName)
            ct++
            if(!viewModel.checkEditTextEmpty(edittextList)){ // if title and price is present
                if(food_category!=null){  // if food category selected
                    if (imageURI != null) {  // if timage uploaded properly
                        val filename = UUID.randomUUID()
                        viewModel.uploadImage(imageURI!!, filename = filename.toString())
                        imageURI=null
                    }else{
                        Toast.makeText(requireContext(),"Select a picture", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(),"Select a category", Toast.LENGTH_SHORT).show()
                }
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