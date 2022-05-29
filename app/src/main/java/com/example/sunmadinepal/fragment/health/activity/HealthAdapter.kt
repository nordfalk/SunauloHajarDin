package com.example.sunmadinepal.fragment.health.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.R
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.model.loadImage
import com.google.android.material.card.MaterialCardView

class HealthAdapter ( private val context: Context,private val mList: List<RecipesData>, val parent: ViewGroup?) :
RecyclerView.Adapter<HealthAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipedetails, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user =mList.get(position)

        loadImage(holder.imageView,user.itemImage)
        Log.d("TAG",  user.itemImage)
        // sets the text to the textview from our itemHolder class
        holder.textView.text = user.itemName //ItemsViewModel.itemName
//        holder.textViewt.text = user.itemDescription.replace("_n", "\n")

        holder.healthInfoDetails.setOnClickListener { v ->
            val intent = Intent(v.context, HealthDetailActivity::class.java)
            intent.putExtra("Image1",user.itemImage)
            intent.putExtra("Title", mList.get(holder.bindingAdapterPosition).getItemName())
            intent.putExtra("Description", mList[holder.bindingAdapterPosition].getItemDescription().replace("_n", "\n"))
            v.context.startActivity(intent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.IvImage)
        val textView: TextView = itemView.findViewById(R.id.tvTitle)
        val healthInfoDetails: MaterialCardView = itemView.findViewById(R.id.health_info_details)
//        val textViewt: TextView = itemView.findViewById(R.id.tvDescription)

    }
}
