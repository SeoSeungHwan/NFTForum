package com.router.nftforum.model.repository

import com.router.nftforum.common.GlobalApplication
import com.router.nftforum.model.retrofit.NaverAPIService

/**
 * Data를 일관성있게 받아오는 MyRepository 클래스.
 */
class MyRepository {

    private val naverAPIService: NaverAPIService by lazy {
        GlobalApplication.naverAPIRetrofit.create(NaverAPIService::class.java)
    }

    suspend fun getNaverNews(query: String, display: Int, start: Int, sort: String) =
        naverAPIService.getNaverNews(query = query, display = display, start = start, sort = sort)


}