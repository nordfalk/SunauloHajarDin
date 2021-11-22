package com.example.sunmadinepal.model

import android.widget.TextView
import com.example.sunmadinepal.R

data class RecipesData(
    public var itemImage: Int,
    public var itemName: String,
    public var itemDescription: String,

    ) {


    /*
    val image: Int, val text: String
     */

    /*
    val itemDescription: String,
    val itemImage: Int,
    val itemName: String
     */

    @JvmName("getItemName1")
    public fun getItemName(): String {
        return itemName
    }

    @JvmName("setItemName1")
    fun setItemName(itemName: String) {
        this.itemName = itemName
    }

    @JvmName("getItemImage1")
    fun getItemImage(): Int {
        return itemImage
    }

    @JvmName("setItemImage1")
    fun setItemImage(itemImage: Int) {
        this.itemImage = itemImage
    }

    @JvmName("getItemDescription1")
    fun getItemDescription(): String {
        return itemDescription
    }

    @JvmName("setItemDescription1")
    fun setItemDescription(itemDescription: String) {
        this.itemDescription = itemDescription
    }


}