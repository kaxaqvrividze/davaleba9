package com.example.davaleba9android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.davaleba9android.dao.RoomRepository
import com.example.davaleba9android.model.DataUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EdAyVwMl @Inject constructor(private val repository: RoomRepository):
    ViewModel() {

    fun getDataUserById(id: Int): DataUser {
        return repository.getDataUserById(id)
    }

    fun updateRecord(dataUser: DataUser) {
        repository.updateRecord(dataUser)
    }

    fun deleteRecord(dataUser: DataUser) {
        repository.deleteRecord(dataUser)
    }
}