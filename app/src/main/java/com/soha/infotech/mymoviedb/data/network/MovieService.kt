package com.soha.infotech.mymoviedb.data.network

import com.soha.infotech.mymoviedb.data.models.Movie
import retrofit2.http.GET
import retrofit2.http.Query

//Step4: Create an API Interface

interface MovieService {

    @GET("3/movie/popular")
    suspend fun getAllMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Movie
}