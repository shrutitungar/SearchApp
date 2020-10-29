package com.sample.searchapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.sample.searchapp.data.ImageResult
import com.sample.searchapp.data.SearchResponse
import com.sample.searchapp.data.local.SearchAppDB
import com.sample.searchapp.data.local.dao.ImageDAO
import com.sample.searchapp.data.remote.SearchAPIRemoteDataSource
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SearchRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var mSearchAPIRemoteDataSource: SearchAPIRemoteDataSource? = SearchAPIRemoteDataSource()
    private var mImageDAO: ImageDAO?

    init {
        val db = SearchAppDB.getInstance(application)
        mImageDAO = db.imageDAO
    }

    fun getSearchResponse(
        pageCount: Int?,
        query: String?
    ): Observable<SearchResponse?>? {
        return mSearchAPIRemoteDataSource?.getSearchResponse(pageCount, query)
    }

    fun getImageInfo(id: String): LiveData<ImageResult>? {
        return mImageDAO?.getImageInfo(id)
    }

    fun setImageInfo(imageResult: ImageResult) {
        launch {
            withContext(Dispatchers.IO) {
                mImageDAO?.setImageInfo(imageResult)
            }
        }
    }
}