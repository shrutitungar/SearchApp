package com.sample.searchapp.data.remote

import com.sample.searchapp.data.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchAPIEndPoint {

    @GET("/3/gallery/search/{pageCount}")
    fun getSearchResponse(
        @Path("pageCount") pageCount: Int?,
        @Query("q") query: String?
    ): Observable<SearchResponse?>?
}