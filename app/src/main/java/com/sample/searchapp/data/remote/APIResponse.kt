package com.sample.searchapp.data.remote

import com.sample.searchapp.data.SearchResponse

class APIResponse(
    var status: Status? = null,
    var data: Any? = null,
    var error: Throwable? = null
) {

    fun loading(): APIResponse {
        return APIResponse(Status.LOADING, null, null)
    }

    fun success(data: SearchResponse?): APIResponse {
        return APIResponse(Status.SUCCESS, data, null)
    }

    fun error(error: Throwable): APIResponse {
        return APIResponse(Status.ERROR, null, error)
    }

    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}