package com.example.sunmadinepal.fragment.child.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "add_child")
data class AddChildModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "child_name")
    val childName: String,

    @ColumnInfo(name = "birth_date")
    val birthDate: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "allergies")
    val allergies: String,

    @ColumnInfo(name = "image")
    val image: Uri?=null
) :Serializable{
    constructor(
        childName: String,
        birthDate: String,
        gender: String,
        allergies: String,
        image: Uri,

    ) : this(
        0, childName,
        birthDate,
        gender,
        allergies,
        image
    )

    override fun toString(): String {
        return childName
    }
}

data class EmptyModel(
    val title : String
)