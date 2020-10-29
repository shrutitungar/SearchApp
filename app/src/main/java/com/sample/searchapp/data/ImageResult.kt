package com.sample.searchapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "image_info")
data class ImageResult(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk_id")
    var pk_id: Int,

    @SerializedName("id")
    @ColumnInfo(name = "image_id")
    var image_id: String? = null,

    @SerializedName("link")
    @ColumnInfo(name = "link")
    var link: String? = null,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    var type: String? = null,

    @SerializedName("comment")
    @ColumnInfo(name = "comment")
    var comment: String? = null
) : Serializable
