package com.poojatn.memescasestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns.WEB_URL
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.poojatn.memescasestudy.adapter.MemeListAdapter
import com.poojatn.memescasestudy.modules.Meme
import com.poojatn.memescasestudy.repository.MemesRepository
import com.poojatn.memescasestudy.viewmodule.MemesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var memesRepository: MemesRepository
    lateinit var memesList: List<Meme>
    private val postViewModel: MemesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        postViewModel.response.observe(this, Observer {
            val adapter = MemeListAdapter(this, it)
            viewPager.adapter = adapter
            memesList = it
        })
//    }
//}

        val next = findViewById<ImageView>(R.id.btnnext)
        next.setOnClickListener {
            viewPager.currentItem++
        }

        val prev = findViewById<ImageView>(R.id.btnprevious)
        prev.setOnClickListener {
            if (viewPager.currentItem == 0) {
                Toast.makeText(this, "this is first meme", Toast.LENGTH_SHORT).show()

            } else {
                viewPager.currentItem--
            }
        }
        val share = findViewById<ImageView>(R.id.sharebtn)
        share.setOnClickListener {
            postViewModel.response.observe(this){
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, " share this meme : ${it[viewPager.currentItem].name} ${it[viewPager.currentItem].url}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        val delete = findViewById<ImageView>(R.id.deletebtn)
        delete.setOnClickListener {
            Toast.makeText(this, "This Meme is got deleted",Toast.LENGTH_LONG).show()
//            CoroutineScope(Dispatchers.IO).launch{
//                println(memesList)
//                memesRepository.deleteMeme(memesList[viewPager.currentItem])
//            }
            val responsedata=postViewModel.responsedelete(memesList[viewPager.currentItem])
            responsedata.observe(this, Observer {
                val adapter2=MemeListAdapter(this,it )
                memesList = it
                viewPager.adapter = adapter2
            })
            memesRepository.deleteMeme(memesList[viewPager.currentItem])
            memesList[viewPager.currentItem++]
        }
        }
    }

