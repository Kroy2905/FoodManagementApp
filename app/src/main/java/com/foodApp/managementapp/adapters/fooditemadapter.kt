package com.foodApp.managementapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodApp.managementapp.R
import com.foodApp.managementapp.models.fooditemResponse
import java.util.*

class fooditemadapter(private val data: fooditemResponse,context: Context) : RecyclerView.Adapter<fooditemadapter.ViewHolder>() {

    // Create a ViewHolder class that holds references to the views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.food_image)
        val title: TextView = itemView.findViewById(R.id.food_title)
        val price: TextView = itemView.findViewById(R.id.food_price)
        val ratiing: TextView = itemView.findViewById(R.id.food_rating)
    }

    // Inflate the view holder layout and return a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fooditemlayout, parent, false)
        return ViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]


        Glide.with(holder.itemView.context/* context */)
            //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
            .load(item.foodImgUrl)
            .error(R.drawable.app_logo)
            .into(holder.imageView)


        holder.title.text=item.foodTitle
        val random = Random()
        val randomNumber = (1.0f + random.nextFloat() * 3.9f).toString()
        holder.ratiing.text = randomNumber.substring(0, minOf(randomNumber.length, 3))
        holder.price.text= item.foodPrice.toString()
    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }
}
