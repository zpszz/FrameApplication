package com.jpc.flowdemo.network

import com.jpc.flowdemo.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("/article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int):ArticleResponse
}