package com.example.sunmadinepal.fragment.child.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.sunmadinepal.fragment.child.model.AddChildModel

@Dao
interface AddChildDAo {

    @Insert(onConflict = REPLACE)
    suspend fun addChild(addChildModel: AddChildModel)

    @Query("SELECT * FROM add_child")
    fun getAllChildData(): LiveData<List<AddChildModel>>


    @Query("UPDATE add_child SET child_name =:childName ,birth_date= :birthDate, gender = :gender, allergies = :allergies,image=:image WHERE id =:id")
    fun updateChild(
        id : Int,
        childName : String,
        birthDate: String,
        gender :String,
        allergies : String,
        image : String
    )
}