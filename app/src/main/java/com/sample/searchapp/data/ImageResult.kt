package com.sample.searchapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "image_info")
data class ImageResult(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: String? = null,

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
