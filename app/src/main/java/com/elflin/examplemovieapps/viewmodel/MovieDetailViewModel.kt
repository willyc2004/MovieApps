package com.elflin.examplemovieapps.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elflin.examplemovieapps.data.DataSource
import com.elflin.examplemovieapps.model.Movie
import com.elflin.examplemovieapps.repository.MovieDBContainer
import kotlinx.coroutines.launch

sealed interface MovieDetailUiState{
    data class Success(val data: Movie): MovieDetailUiState
    object Error: MovieDetailUiState
    object Loading: MovieDetailUiState
}

class MovieDetailViewModel(): ViewModel() {

    private lateinit var data: Movie
    var movieDetailUiState: MovieDetailUiState by mutableStateOf(MovieDetailUiState.Loading)
        private set

    fun getMoviebyId(id: Int){
        viewModelScope.launch {
            try {
                data = MovieDBContainer().movieDBRepository.getMovieDetail(id)
                movieDetailUiState = MovieDetailUiState.Success(data)
            }catch (e: Exception){
                movieDetailUiState = MovieDetailUiState.Error
            }

        }
    }
}