package com.example.sunmadinepal.database.local.repository

import androidx.lifecycle.LiveData
import com.example.sunmadinepal.fragment.child.dao.AddChildDAo
import com.example.sunmadinepal.fragment.child.model.AddChildModel

class AddChildRepository(private val addChildDAo: AddChildDAo) {

    val childData: LiveData<List<AddChildModel>> = addChildDAo.getAllChildData()

    suspend fun addChildRepository(addChildModel: AddChildModel) {
        addChildDAo.addChild(addChildModel)
    }

    fun updateChildRepository(
        id: Int, childName: String,
        birthDate: String,
        gender: String,
        allergies: String,
        image: String
    ) {
        addChildDAo.updateChild(
            id,
            childName,
            birthDate,
            gender,
            allergies,
            image
        )
    }


}