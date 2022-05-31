package com.example.davaleba9android.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.davaleba9android.dao.AppDao
import com.example.davaleba9android.model.DataUser
import com.example.davaleba9android.model.RepList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RRep @Inject constructor(
    private val retroServiceInterface: SerInt,
    private val appDao: AppDao
) {

    fun getAllRecords(): LiveData<List<DataUser>> {
        Log.d("logging", "get all records from LiveData")
        return appDao.getAllRecords()
    }

    fun insertRecord(dataUser: DataUser) {
        Log.d("logging", "insert record to DB")
        appDao.insertRecords(dataUser)
    }

    //get data from api
    fun makeApiCall(query: String?) {
        val call: Call<RepList> = retroServiceInterface.getDataFromAPI(query!!)
        call?.enqueue(object : Callback<RepList> {
            override fun onResponse(
                call: Call<RepList>,
                response: Response<RepList>
            ) {
                if (response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.data?.forEach {
                        insertRecord(it)
                    }
                    Log.d("logging", "Success")
                }
            }

            override fun onFailure(call: Call<RepList>, t: Throwable) {
                Log.d("logging", "Failure")
            }
        })
    }
}