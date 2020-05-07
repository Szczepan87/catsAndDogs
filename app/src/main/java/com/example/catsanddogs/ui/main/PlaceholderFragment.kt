package com.example.catsanddogs.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.catsanddogs.R
import com.example.catsanddogs.utility.AnimalType
import com.example.catsanddogs.utility.PictureLoader
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment(private val animalType: AnimalType) : ScopedFragment() {

    private val pageViewModel: PageViewModel = get(PageViewModel::class.java)
    private val pictureLoader = PictureLoader(this)

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
                pictureLoader.loadPictureFromURL(it.first().url, swiperefresh, image)
            })
    }

    private fun setUpDogPicture() {
        pageViewModel.dogPicture.observe(
            viewLifecycleOwner,
            Observer {
                pictureLoader.loadPictureFromURL(it.url, swiperefresh, image)
            })
    }
}