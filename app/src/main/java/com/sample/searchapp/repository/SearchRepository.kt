package com.sample.searchapp.repository

import com.sample.searchapp.data.SearchResult
import com.sample.searchapp.data.remote.SearchAPIRemoteDataSource
import io.reactivex.Observable

class SearchRepository {

    private var mSearchAPIRemoteDataSource: SearchAPIRemoteDataSource? = SearchAPIRemoteDataSource()

    fun getFilters(
        pageCount: Int?,
        query: String?
    ): Observable<List<SearchResult>?>? {
        return mSearchAPIRemoteDataSource?.getSearchResult(pageCount, query)
    }
}