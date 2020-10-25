package com.sample.searchapp.repository

import com.sample.searchapp.data.SearchResponse
import com.sample.searchapp.data.remote.SearchAPIRemoteDataSource
import io.reactivex.Observable

class SearchRepository {

    private var mSearchAPIRemoteDataSource: SearchAPIRemoteDataSource? = SearchAPIRemoteDataSource()

    fun getSearchResponse(
        pageCount: Int?,
        query: String?
    ): Observable<SearchResponse?>? {
        return mSearchAPIRemoteDataSource?.getSearchResponse(pageCount, query)
    }
}