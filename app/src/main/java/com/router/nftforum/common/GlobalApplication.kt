package com.router.nftforum.common

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.router.nftforum.model.retrofit.interceptor.InterceptorForNaverAPIAuth
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GlobalApplication: Application() {

    companion object {
        lateinit var naverAPIRetrofit: Retrofit
        lateinit var naverAPIOkHttpClient: OkHttpClient
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofit()

        // 앱 내 다크모드 비활성화
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initRetrofit() {

        val naverAPIURL = "https://openapi.naver.com/v1/"

        naverAPIOkHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(InterceptorForNaverAPIAuth())
            .build()

        naverAPIRetrofit = Retrofit.Builder().baseUrl(naverAPIURL).client(naverAPIOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}