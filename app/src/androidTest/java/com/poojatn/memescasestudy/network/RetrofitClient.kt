package com.poojatn.memescasestudy.network

import com.poojatn.memescasestudy.diprovide.AppModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppModule.providesBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}