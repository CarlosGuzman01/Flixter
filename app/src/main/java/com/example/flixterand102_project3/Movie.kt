package com.example.flixterand102_project3

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("original_title")
    var movieTitle: String? = null

    @SerializedName("poster_path")
    var moviePosterImageUrl: String? = null

    @SerializedName("overview")
    var overview: String? = null


}