package com.sample.searchapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageResult(

    @SerializedName("link")
    var link: String? = null,

    @SerializedName("type")
    var type: String? = null
) : Serializable
