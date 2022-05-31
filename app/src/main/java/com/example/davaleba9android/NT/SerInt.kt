package com.example.davaleba9android.network

import com.example.davaleba9android.model.RepList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SerInt {

    @GET("users")
    fun getDataFromAPI(@Query("page") query: String): Call<RepList>
}