package com.soha.infotech.mymoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soha.infotech.mymoviedb.data.models.Movie
import com.soha.infotech.mymoviedb.data.models.MovieInfo
import com.soha.infotech.mymoviedb.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Step7: Create a ViewModel

@HiltViewModel
class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun fetchMovies(apiKey: String, page: Int) {
        viewModelScope.launch {
            _movie.value = movieRepository.getMovie(apiKey = apiKey, page = page)
        }
    }

    val _movieInfo = MutableLiveData<MovieInfo>()
    val movieInfo: LiveData<MovieInfo>
        get() = _movieInfo

    // Set data for details screen when user click on the list items
    fun setMovieInfo(movieInfo: MovieInfo) {
        _movieInfo.value = movieInfo
    }

}