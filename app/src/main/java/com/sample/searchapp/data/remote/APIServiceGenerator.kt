package com.sample.searchapp.data.remote

import com.sample.searchapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIServiceGenerator {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit = builder.build()

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.MINUTES)
        .readTimeout(3, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)

    fun <S> createService(
        serviceClass: Class<S>
    ): S {
        if (BuildConfig.DEBUG)
            logging.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = AuthenticationInterceptor()
        httpClient.interceptors().clear()
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit.create(serviceClass)
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }
}