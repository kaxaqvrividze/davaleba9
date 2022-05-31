package com.example.davaleba9android.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.davaleba9android.model.DataUser
import com.example.davaleba9android.model.TypeConverterDataUser

@Database(entities = [DataUser::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterDataUser::class)
abstract class AppDb : RoomDatabase() {

    abstract fun getAppDao(): AppDao

    companion object {
        private var DB_INSTANCE: AppDb? = null

        fun getAppDBInstance(context: Context): AppDb {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "APP_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            Log.d("logging", "DB created")
            return DB_INSTANCE!!
        }
    }
}