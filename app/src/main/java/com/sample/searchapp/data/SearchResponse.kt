package com.sample.searchapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchResponse(
    @SerializedName("data")
    var data: List<SearchResult>
):Serializable