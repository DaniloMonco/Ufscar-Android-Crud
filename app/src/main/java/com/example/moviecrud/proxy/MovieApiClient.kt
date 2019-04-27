package com.example.moviecrud.proxy

import com.example.moviecrud.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MovieApiClient {

    @GET("ufscar") fun getMovies(): Observable<List<Movie>>
    @POST("ufscar") fun addMovie(@Body movie: Movie): Completable
    @DELETE("ufscar/{id}") fun deleteMovie(@Path("id") id: String) : Completable
    @PUT("ufscar/{id}") fun updateMovie(@Path("id")id: String, @Body movie: Movie) : Completable

    companion object {

        fun create(): MovieApiClient {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hm-ride-api.wappa.com.br/api/1.0/")
                .build()

            return retrofit.create(MovieApiClient::class.java)
        }
    }
}