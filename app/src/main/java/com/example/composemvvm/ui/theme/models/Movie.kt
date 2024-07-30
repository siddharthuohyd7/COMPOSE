package com.example.composemvvm.ui.theme.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Movie(
    val record: List<MovieItem>
)

@Parcelize
data class MovieItem(
    val actors: String,
    val director: String,
    val genres: List<String>,
    val id: Int,
    val plot: String,
    val posterUrl: String,
    val runtime: String,
    val title: String,
    val year: String
) : Parcelable