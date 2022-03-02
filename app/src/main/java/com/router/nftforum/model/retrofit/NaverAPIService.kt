package com.router.nftforum.model.retrofit

import com.router.nftforum.model.retrofit.data.NewsItemsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverAPIService {

    @GET("search/news.json")
    suspend fun getNaverNews(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("sort") sort: String
    ): NewsItemsResponseModel
}