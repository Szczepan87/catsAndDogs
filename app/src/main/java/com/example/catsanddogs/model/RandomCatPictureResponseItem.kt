package com.example.catsanddogs.model


import com.google.gson.annotations.SerializedName

data class RandomCatPictureResponseItem(
    @SerializedName("breeds")
    val breeds: List<Any>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)