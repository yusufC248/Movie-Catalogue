package com.submission.yusuf.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object DataHelper {


    fun setImageView(context: Context, imgPath: String, imgView: ImageView){
        Glide.with(context).clear(imgView)
        Glide.with(context).load(imgPath).into(imgView)
    }
}