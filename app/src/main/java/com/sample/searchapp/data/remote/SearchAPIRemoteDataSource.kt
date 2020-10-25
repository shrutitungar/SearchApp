package com.sample.searchapp.data.remote

import com.sample.searchapp.data.SearchResult
import io.reactivex.Observable

class SearchAPIRemoteDataSource {

    var mSearchAPIEndPoint: SearchAPIEndPoint? =
        APIServiceGenerator().createService(SearchAPIEndPoint::class.java)

    fun getSearchResult(
        pageCount: Int?,
        query: String?
    ): Observable<List<SearchResult>?>? {
        return mSearchAPIEndPoint?.getSearchResults(pageCount, query)
    }
}