package com.example.catsanddogs.utility

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class PictureLoader(private val fragment: Fragment) {

    fun loadPictureFromURL(
        url: String,
        swipeRefreshLayout: SwipeRefreshLayout,
        imageView: ImageView
    ) {
        GlideApp.with(fragment)
            .load(url)
            .dontTransform()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    swipeRefreshLayout.isRefreshing = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    swipeRefreshLayout.isRefreshing = false
                    return false
                }
            })
            .into(imageView)
    }
}