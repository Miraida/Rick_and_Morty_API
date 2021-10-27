package com.geek.rick_and_morty_api.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}


fun View.visibility(isTrue: Boolean) {
    if (isTrue) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}