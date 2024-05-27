package com.duodinamika.advweek4.view

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(imageView)
    } else {
        Log.e("XXX", "loadPhotoURL: ----- ", )
    }
}