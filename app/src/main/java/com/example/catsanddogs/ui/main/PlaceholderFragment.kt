package com.example.catsanddogs.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.catsanddogs.R
import com.example.catsanddogs.utility.AnimalType
import com.example.catsanddogs.utility.GlideApp
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment(private val animalType: AnimalType) : ScopedFragment() {

    private val pageViewModel: PageViewModel = get(PageViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (animalType == AnimalType.DOG) {
            launch { pageViewModel.loadDogPicture() }
        } else launch { pageViewModel.loadCatPicture() }

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swiperefresh.isRefreshing = true
        if (animalType == AnimalType.DOG) {
            setUpDogPicture()
        } else {
            setUpCatPicture()
        }
        setUpRefreshLayout()
    }

    private fun setUpRefreshLayout() {
        swiperefresh.setOnRefreshListener {
            if (animalType == AnimalType.DOG) {
                launch { pageViewModel.loadDogPicture() }
            } else launch { pageViewModel.loadCatPicture() }
        }
    }

    private fun setUpCatPicture() {
        pageViewModel.catPicture.observe(
            viewLifecycleOwner,
            Observer {
                GlideApp.with(this)
                    .load(it.first().url)
                    .dontTransform()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            swiperefresh.isRefreshing = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            swiperefresh.isRefreshing = false
                            return false
                        }
                    })
                    .into(image)
            })
    }

    private fun setUpDogPicture() {
        pageViewModel.dogPicture.observe(
            viewLifecycleOwner,
            Observer {
                GlideApp.with(this)
                    .load(it.url)
                    .dontTransform()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            swiperefresh.isRefreshing = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            swiperefresh.isRefreshing = false
                            return false
                        }
                    })
                    .into(image)
            })
    }
}