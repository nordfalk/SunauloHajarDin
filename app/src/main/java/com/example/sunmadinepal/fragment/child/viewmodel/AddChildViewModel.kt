package com.example.sunmadinepal.fragment.child.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sunmadinepal.database.local.AppDatabase
import com.example.sunmadinepal.database.local.repository.AddChildRepository
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddChildViewModel(application: Application) : AndroidViewModel(application) {
    val getAllChildData: LiveData<List<AddChildModel>>
    private val repository: AddChildRepository


    init {
        val doctorAppointmentDao = AppDatabase.getDatabase(application).addChildDao()
        repository = AddChildRepository(doctorAppointmentDao)
        getAllChildData = repository.childData
    }


    fun addChildViewModel(addChildModel: AddChildModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChildRepository(addChildModel)
        }
    }

    fun updateChildViewModel(
        id: Int, childName: String,
        birthDate: String,
        gender: String,
        allergies: String,
        image: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateChildRepository(
                id,
                childName,
                birthDate,
                gender,
                allergies,
                image
            )
        }
    }
}