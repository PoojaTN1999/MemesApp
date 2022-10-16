package com.poojatn.memescasestudy.network

import com.poojatn.memescasestudy.modules.Memes
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class  MemesService  constructor(private val retrofit : Retrofit): MemeEndPoint {
    private val endpoint by lazy {  retrofit.create(MemeEndPoint::class.java)  }
    override fun getMeme() : Call<Response<Memes>> {
        return endpoint.getMeme()
    }
}