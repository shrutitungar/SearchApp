package com.sample.searchapp.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        builder.header("Authorization", "Client-ID 137cda6b5008a7c")
        val request = builder.build()
        return chain.proceed(request)
    }
}