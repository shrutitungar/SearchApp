package com.sample.searchapp.data.remote

import com.sample.searchapp.data.SearchResult

class APIResponse(
     var status: Status? = null,
     var data: Any? = null,
     var error: Throwable? = null
) {

    fun loading(): APIResponse {
        return APIResponse(Status.LOADING, null, null)
    }

    fun success(data: List<SearchResult>?): APIResponse {
        return APIResponse(Status.SUCCESS, data, null)
    }

    fun error(error: Throwable): APIResponse {
        return APIResponse(Status.ERROR, null, error)
    }

    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}