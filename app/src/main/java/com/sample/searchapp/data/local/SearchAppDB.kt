package com.sample.searchapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.searchapp.data.ImageResult
import com.sample.searchapp.data.local.dao.ImageDAO

@Database(entities = [ImageResult::class], version = 1, exportSchema = false)
abstract class SearchAppDB : RoomDatabase() {

    abstract val imageDAO: ImageDAO

    companion object {

        @Volatile
        private var INSTANCE: SearchAppDB? = null

        fun getInstance(context: Context): SearchAppDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SearchAppDB::class.java,
                        "search_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}