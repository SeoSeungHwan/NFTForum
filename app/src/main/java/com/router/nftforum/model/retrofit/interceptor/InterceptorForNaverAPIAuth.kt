package com.router.nftforum.model.retrofit.interceptor

import com.router.nftforum.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class InterceptorForNaverAPIAuth(): Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("X-Naver-Client-Id", BuildConfig.X_Naver_Client_Id)
            .addHeader("X-Naver-Client-Secret",BuildConfig.X_Naver_Client_Secret)
            .build()
        proceed(newRequest)
    }
}
