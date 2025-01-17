package com.example.sunmadinepal.model

import android.widget.TextView
import com.example.sunmadinepal.R
import java.io.File
import java.io.Serializable

data class RecipesData(
    var itemImage: String,
    var itemName: String,
    var itemDescription: String,
    var itemDirection : String

    ) : Serializable {

    @JvmName("getItemName1")
    public fun getItemName(): String {
        return itemName
    }

    @JvmName("setItemName1")
    fun setItemName(itemName: String) {
        this.itemName = itemName
    }

    @JvmName("getItemImage1")
    fun getItemImage(): String {
        return itemImage
    }

    @JvmName("setItemImage1")
    fun setItemImage(itemImage: String) {
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


    @JvmName("itemDirection")
    fun getItemDirection(): String {
        return itemDirection
    }

}