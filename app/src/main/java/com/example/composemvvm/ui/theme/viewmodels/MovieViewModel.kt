package com.example.composemvvm.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.composemvvm.ui.theme.models.MovieItem
import com.example.composemvvm.ui.theme.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesDataStateFlow = MutableStateFlow<List<MovieItem>>(emptyList())

    val moviesDataStateFlow = _moviesDataStateFlow

    private val dispatchers = Dispatchers.IO

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.getAllMovies().flowOn(dispatchers).catch { error ->
                println("******************************* ERROR ********************** ${error.message}")

            }.collect {
                list-> _moviesDataStateFlow.value = list.record

            }
        }
    }
}