package com.poojatn.memescasestudy.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.poojatn.memescasestudy.modules.Meme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class MemeDaoTest {
    private lateinit var database: MemesDatabase
    private lateinit var dao: MemeDao
    @Before
    fun setup(){
        database=Room.inMemoryDatabaseBuilder(
           ApplicationProvider.getApplicationContext<Context>() ,
            MemesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao= database.memeDao()!!
    }
    @After
    @Throws(IOException::class)
    fun teardown(){
        database.close()
    }
    @Test
    fun insertMeme()= runBlocking {
        val memeItem=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.insertMeme(listMeme)
       val getMemeItems=dao.getAllMemes()
        val byName=dao.getAllMemes()
        assert(byName.size == getMemeItems.size)
    }
    @Test
    fun getAllMemes()= runBlocking {
        val memeItem=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.insertMeme(listMeme)
        val totalItems=dao.getAllMemes()
        assert(totalItems.isNotEmpty())
    }
    @Test
    fun deleteMemes()= runBlocking {
        val memeItem=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme("181913649",2,1200,"Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.deleteMemes(memeItem1)
        assert(listMeme.contains(memeItem1))
    }
}