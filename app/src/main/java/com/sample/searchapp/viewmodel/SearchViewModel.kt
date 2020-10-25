package com.sample.searchapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sample.searchapp.data.remote.APIResponse
import com.sample.searchapp.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private var searchRepository: SearchRepository? = SearchRepository()
    private val mSearchResultsLiveData: MutableLiveData<APIResponse> = MutableLiveData()

    //region get search results
    fun getSearchResultsLiveData(): MutableLiveData<APIResponse>? {
        return mSearchResultsLiveData
    }

    fun getSearchResults(
        pageCount: Int?,
        query: String?
    ) {
        searchRepository?.getFilters(
            pageCount, query
        )?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                mSearchResultsLiveData.postValue(APIResponse().loading())
            }
            ?.subscribe(
                { result -> mSearchResultsLiveData.postValue(APIResponse().success(result)) },
                { throwable -> mSearchResultsLiveData.postValue(APIResponse().error(throwable)) }
            )?.let { disposables.add(it) }
    }
    //endregion
}