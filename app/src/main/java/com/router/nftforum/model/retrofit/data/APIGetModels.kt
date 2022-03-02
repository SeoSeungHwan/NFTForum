package com.router.nftforum.model.retrofit.data

import com.google.gson.annotations.SerializedName
import com.router.nftforum.binding.NaverNewsHolderModel

data class NewsItemsResponseModel(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<NaverNewsHolderModel>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)