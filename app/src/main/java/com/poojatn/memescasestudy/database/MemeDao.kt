package com.poojatn.memescasestudy.database

import androidx.room.*
import com.poojatn.memescasestudy.modules.Meme

@Dao
interface MemeDao {

    @Query("SELECT * FROM memes")
    suspend fun getAllMemes(): List<Meme>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeme(meme: List<Meme>)

    @Delete
    suspend fun deleteMemes(meme: Meme)


}