package com.poojatn.memescasestudy.modules

import com.google.gson.annotations.SerializedName

data class MemeList(
    @SerializedName("memes")
    val memes: List<Meme>
)