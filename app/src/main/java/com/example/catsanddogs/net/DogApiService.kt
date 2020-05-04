package com.example.catsanddogs.net

import com.example.catsanddogs.model.RandomDogPictureResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DogApiService {

    @GET("woof.json")
    fun getRandomDogPicture(): Deferred<RandomDogPictureResponse>
}