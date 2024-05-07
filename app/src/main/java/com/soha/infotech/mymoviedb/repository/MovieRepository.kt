package com.soha.infotech.mymoviedb.repository

import com.soha.infotech.mymoviedb.data.models.Movie
import com.soha.infotech.mymoviedb.data.network.MovieService
import javax.inject.Inject

//Step6: Create a Repository

class MovieRepository @Inject constructor(val movieService: MovieService) {

    suspend fun getMovie(apiKey: String, page: Int): Movie {
        return movieService.getAllMovie(apiKey, page)
    }
}