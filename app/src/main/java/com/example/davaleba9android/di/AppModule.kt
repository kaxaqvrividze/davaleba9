package com.example.davaleba9android.di

import android.app.Application
import com.example.davaleba9android.dao.AppDao
import com.example.davaleba9android.dao.AppDb
import com.example.davaleba9android.network.SerInt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): AppDb {
        return AppDb.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDb): AppDao {
        return appDatabase.getAppDao()
    }

    val BASE_URL = "https://reqres.in/api/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): SerInt {
        return retrofit.create(SerInt::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}