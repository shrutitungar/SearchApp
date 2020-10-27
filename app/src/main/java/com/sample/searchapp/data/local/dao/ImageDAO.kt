package com.sample.searchapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.searchapp.data.ImageResult

@Dao
interface ImageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setImageInfo(imageResult: ImageResult)

    @Query("SELECT * FROM image_info WHERE id = :id")
    fun getImageInfo(id: String): ImageResult
}