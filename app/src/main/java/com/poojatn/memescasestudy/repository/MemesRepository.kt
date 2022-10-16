package com.poojatn.memescasestudy.repository

import com.poojatn.memescasestudy.database.MemeDaoImpl
import com.poojatn.memescasestudy.modules.Meme
import com.poojatn.memescasestudy.network.MemeServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MemesRepository @Inject constructor(private val apiService: MemeServiceImpl,
                                          private val memeDao: MemeDaoImpl){
    fun getMeme() : Flow<List<Meme>> = flow {


        var allListmemes=memeDao.getAllMemes();//data from the Db




        if(allListmemes.size>0)
        {
            emit(allListmemes)
        }else {
            val response = apiService.getMeme()

            memeDao.insertMeme(response.body()?.memeList!!.memes)
            var allListmemes=memeDao.getAllMemes();//data from the Db

            emit(allListmemes);
        }


    }.flowOn(Dispatchers.IO)

//    suspend fun deleteMeme(meme: Meme) = memeDao.deleteMemes(meme)
    fun deleteMeme(meme: Meme):Flow<List<Meme>> = flow {
        var deletememe= memeDao.deleteMemes(meme)
        var allListmeme=memeDao.getAllMemes()

        emit(allListmeme)
    }

}