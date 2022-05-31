package com.example.davaleba9android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.davaleba9android.model.DataUser
import com.example.davaleba9android.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAyVwMl @Inject constructor(private val repository: RetroRepository):
    ViewModel() {

    fun getAllRepositoryList(): LiveData<List<DataUser>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall("2")
    }
}