package com.example.catsanddogs.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catsanddogs.model.RandomCatPictureResponse
import com.example.catsanddogs.model.RandomDogPictureResponse
import com.example.catsanddogs.net.CatApiService
import com.example.catsanddogs.net.DogApiService
import com.example.catsanddogs.utility.NoInternetConnectionException

class PageViewModel(
    private val dogApiService: DogApiService,
    private val catApiService: CatApiService
) : ViewModel() {

    private val _dogPicture = MutableLiveData<RandomDogPictureResponse>()
    private val _catPicture = MutableLiveData<RandomCatPictureResponse>()

    val dogPicture: LiveData<RandomDogPictureResponse>
        get() = _dogPicture
    val catPicture: LiveData<RandomCatPictureResponse>
        get() = _catPicture

    suspend fun loadDogPicture() {
        try {
            val loadedDogPictureResponse = dogApiService.getRandomDogPicture().await()
            _dogPicture.postValue(loadedDogPictureResponse)
        } catch (e: NoInternetConnectionException) {
            Log.d("Internet:", "No connection!", e)
        }
    }

    suspend fun loadCatPicture() {
        try {
            val loadedCatPicture = catApiService.getRandomCatPicture().await()
            _catPicture.postValue((loadedCatPicture))
        } catch (e: NoInternetConnectionException) {
            Log.d("Internet:", "No connection!", e)
        }
    }
}