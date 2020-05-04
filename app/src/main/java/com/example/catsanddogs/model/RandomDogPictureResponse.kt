package com.example.catsanddogs.model


import com.google.gson.annotations.SerializedName

data class RandomDogPictureResponse(
    @SerializedName("fileSizeBytes")
    val fileSizeBytes: Int,
    @SerializedName("url")
    val url: String
)