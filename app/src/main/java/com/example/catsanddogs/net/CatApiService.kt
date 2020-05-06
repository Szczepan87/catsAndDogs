package com.example.catsanddogs.net

import com.example.catsanddogs.model.RandomCatPictureResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CatApiService {

    @GET("images/search")
    fun getRandomCatPicture(): Deferred<RandomCatPictureResponse>
}