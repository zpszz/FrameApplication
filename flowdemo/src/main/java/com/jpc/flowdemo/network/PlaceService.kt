package com.jpc.flowdemo.network

import com.jpc.flowdemo.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("/v2/place?token=PZyyfyQkGONdBKWL&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") name: String): PlaceResponse
}