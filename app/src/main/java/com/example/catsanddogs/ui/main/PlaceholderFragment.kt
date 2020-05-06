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
import com.example.catsanddogs.utility.GlideApp
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : ScopedFragment() {

    private val pageViewModel: PageViewModel = get(PageViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel.setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        launch { pageViewModel.loadCatPicture() }
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swiperefresh.isRefreshing = true
        pageViewModel.catPicture.observe(
            viewLifecycleOwner,
            Observer {
                GlideApp.with(this)
                    .load(it.first().url)
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
        setUpRefreshLayout()
    }

    private fun setUpRefreshLayout() {
        swiperefresh.setOnRefreshListener {
            launch { pageViewModel.loadCatPicture() }
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}