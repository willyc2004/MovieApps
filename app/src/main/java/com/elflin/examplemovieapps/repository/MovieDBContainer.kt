package com.elflin.examplemovieapps.repository

import com.elflin.examplemovieapps.service.MovieDBService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(private val bearerToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $bearerToken")
            .build()
        return chain.proceed(request)
    }
}

class MovieDBContainer {

    companion object {
        val BASE_IMG = "https://image.tmdb.org/t/p/w500"
    }

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val ACCESS_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjBlYjY2MDZlMjQ3MjQ0NGIzYjllMDg1ZGVlZTdhMSIsInN1YiI6IjY1Mzc0YjljNDFhYWM0MDBhYTA3YzJlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xDmZP8Ff6AdzH7Rm7ed6cB7S04-l5CRI3LtSs1t-puU"

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
            .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: MovieDBService by lazy{
        retrofit.create(MovieDBService::class.java)
    }

    val movieDBRepository:MovieDBRepository by lazy {
        MovieDBRepository(retrofitService)
    }




}
