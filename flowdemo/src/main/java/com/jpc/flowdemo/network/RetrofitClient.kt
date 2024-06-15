package com.jpc.flowdemo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val URL1 = "https://api.caiyunapp.com"
    val URL2 = "https://www.wanandroid.com"
    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            //.client(OkHttpClient.Builder().build())
            .baseUrl(URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createApi(clazz: Class<T>): T{
        return instance.create(clazz) as T
    }

    val placeApi: PlaceService by lazy {
        instance.create(PlaceService::class.java)
    }
}