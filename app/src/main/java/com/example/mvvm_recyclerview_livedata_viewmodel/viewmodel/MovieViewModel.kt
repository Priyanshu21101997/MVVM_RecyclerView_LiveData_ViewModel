package com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_recyclerview_livedata_viewmodel.model.MovieModel
import com.example.mvvm_recyclerview_livedata_viewmodel.network.APIService
import com.example.mvvm_recyclerview_livedata_viewmodel.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel(){

    private val movieList:MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun makeApiCall() {
        val apiService = RetroInstance.getRetroClient()?.create(APIService::class.java)
        val call: Call<List<MovieModel>> = apiService!!.getMovieList("vIF1Szg9Z0wV4sV20bAgw_1Mxuunm2H9i4nLq8IsJwg")

            call.enqueue(object : Callback<List<MovieModel>> {
                override fun onResponse(
                    call: Call<List<MovieModel>>,
                    response: Response<List<MovieModel>>
                ) {
                    Log.d("Dummy","${response.body()}")
                    movieList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                    Log.d("Dummy","${t.message}")

                    movieList.postValue(null)
                }
            })
    }

    fun getMovieListObserver():MutableLiveData<List<MovieModel>>{
        return movieList
    }



}