package com.example.composemvvm

import com.example.composemvvm.ui.theme.models.Movie
import com.example.composemvvm.ui.theme.models.MovieItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface ApiService {

    @GET("/v3/b/66975cc6e41b4d34e4130883")
    suspend fun getAllMovies(): Movie
}