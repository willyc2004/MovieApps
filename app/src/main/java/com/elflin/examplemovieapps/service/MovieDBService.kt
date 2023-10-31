package com.elflin.examplemovieapps.service

import com.elflin.examplemovieapps.model.NowPlaying
import com.elflin.examplemovieapps.model.RawMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @GET("now_playing")
    suspend fun getAllMovie(@Query("page") page: Int = 1, @Query("language") language: String = "en-US" ): NowPlaying

    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): RawMovie
}