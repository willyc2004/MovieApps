package com.elflin.examplemovieapps.repository

import com.elflin.examplemovieapps.model.Movie
import com.elflin.examplemovieapps.model.NowPlaying
import com.elflin.examplemovieapps.service.MovieDBService
import retrofit2.http.Query
import java.text.SimpleDateFormat

class MovieDBRepository (private val movieDBService: MovieDBService){
    suspend fun getAllMovie(page: Int = 1):List<Movie> {
        val ListRawMovie =  movieDBService.getAllMovie().results

        val data = mutableListOf<Movie>()

        for (rawMovie in ListRawMovie) {
            val movie = Movie(
                rawMovie.id,
                rawMovie.overview,
                rawMovie.poster_path,
                SimpleDateFormat("yyyy-MM-dd").parse(rawMovie.release_date),
                rawMovie.title,
                rawMovie.vote_average.toFloat(),
                rawMovie.vote_count
            )

            data.add(movie)
        }
        return data
    }


    suspend fun getMovieDetail(movieId: Int): Movie {
        val rawMovie = movieDBService.getMovieDetail(movieId)

        val data = Movie(
            rawMovie.id,
            rawMovie.overview,
            rawMovie.poster_path,
            SimpleDateFormat("yyyy-MM-dd").parse(rawMovie.release_date),
            rawMovie.title,
            rawMovie.vote_average.toFloat(),
            rawMovie.vote_count
        )

        return data
    }
}