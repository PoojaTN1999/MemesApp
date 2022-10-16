package com.poojatn.memescasestudy.database

import com.poojatn.memescasestudy.modules.Meme
import javax.inject.Inject

class MemeDaoImpl @Inject constructor(private val memeDao: MemeDao?) {
    suspend fun deleteMemes(meme: Meme) = memeDao!!.deleteMemes(meme)
    suspend fun getAllMemes() : List<Meme> = memeDao!!.getAllMemes()

    suspend fun insertMeme(meme: List<Meme>) = memeDao!!.insertMeme(meme)
}