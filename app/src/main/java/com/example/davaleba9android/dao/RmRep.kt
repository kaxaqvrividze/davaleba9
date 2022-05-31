package com.example.davaleba9android.dao

import com.example.davaleba9android.model.DataUser
import javax.inject.Inject

class RmRep @Inject constructor(private val appDao: AppDao) {


    fun getDataUserById(id: Int): DataUser {
        return appDao.getDataUserById(id)
    }

    fun updateRecord(dataUser: DataUser) {
        appDao.updateRecord(dataUser)
    }

    fun deleteRecord(dataUser: DataUser) {
        appDao.deleteRecord(dataUser)
    }
}