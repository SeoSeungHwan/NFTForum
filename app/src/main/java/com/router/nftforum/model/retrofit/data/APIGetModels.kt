package com.router.nftforum.model.retrofit.data

import com.google.gson.annotations.SerializedName


data class NewsItemResponseModel(
    @SerializedName("description")
    val description: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("originallink")
    val originallink: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("title")
    val title: String
)

data class NewsItemsResponseModel(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<NewsItemResponseModel>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)