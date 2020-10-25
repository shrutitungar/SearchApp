package com.sample.searchapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResult(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("link")
    var link: String? = null
) : Serializable