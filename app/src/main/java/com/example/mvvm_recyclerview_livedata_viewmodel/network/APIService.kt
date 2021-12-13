package com.example.mvvm_recyclerview_livedata_viewmodel.network

import com.example.mvvm_recyclerview_livedata_viewmodel.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("photos")
    fun getMovieList(@Query("client_id") client_id:String): Call<List<MovieModel>>

}