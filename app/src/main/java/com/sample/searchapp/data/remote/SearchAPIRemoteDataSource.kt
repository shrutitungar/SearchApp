package com.sample.searchapp.data.remote

import com.sample.searchapp.data.SearchResponse
import io.reactivex.Observable

class SearchAPIRemoteDataSource {

    var mSearchAPIEndPoint: SearchAPIEndPoint? =
        APIServiceGenerator().createService(SearchAPIEndPoint::class.java)

    fun getSearchResponse(
        pageCount: Int?,
        query: String?
    ): Observable<SearchResponse?>? {
        return mSearchAPIEndPoint?.getSearchResponse(pageCount, query)
    }
}