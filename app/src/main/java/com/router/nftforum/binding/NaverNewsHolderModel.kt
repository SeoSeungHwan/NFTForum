package com.router.nftforum.binding

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class NaverNewsHolderModel(
    val description: String,
    val link: String,
    val originallink: String,
    val pubDate: String,
    val title: String
){
    fun changeDateFormat() : String{
        val parseDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
        val formatDateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분")
        val parseDate = parseDateFormat.parse(pubDate)
        return formatDateFormat.format(parseDate);
    }
}