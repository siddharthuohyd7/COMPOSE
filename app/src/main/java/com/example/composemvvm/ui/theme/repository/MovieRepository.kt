package com.example.composemvvm.ui.theme.repository

import com.example.composemvvm.ApiService
import com.example.composemvvm.ui.theme.models.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(val apiService: ApiService) {


    suspend fun getAllMovies() = flow {
        emit(apiService.getAllMovies())
    }
}