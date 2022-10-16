package com.poojatn.memescasestudy.network

import com.poojatn.memescasestudy.modules.Memes
import retrofit2.Response
import javax.inject.Inject

class MemeServiceImpl @Inject
constructor(private val iapiService: iApiService){

    suspend fun  getMeme() : Response<Memes> = iapiService.getMeme()

}