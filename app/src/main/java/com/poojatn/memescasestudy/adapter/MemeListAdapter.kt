package com.poojatn.memescasestudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poojatn.memescasestudy.R
import com.poojatn.memescasestudy.modules.Meme

class MemeListAdapter(val context: Context, val memes : List<Meme>): RecyclerView.Adapter<MemeListAdapter.ViewPagerViewHolder>() {
    class ViewPagerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageview : ImageView = itemView.findViewById<ImageView>(R.id.memeimage)
        val title : TextView = itemView.findViewById(R.id.memeName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meme_layout,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curMeme = memes[position]
        Glide.with(context).load(curMeme.url).into(holder.imageview)
        holder.title.text = curMeme.name

    }
    override fun getItemCount(): Int {
        return memes.size
    }
}

