package com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_recyclerview_livedata_viewmodel.SocialAppRepository
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppDatabase
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.model.MovieModel
import com.example.mvvm_recyclerview_livedata_viewmodel.network.APIService
import com.example.mvvm_recyclerview_livedata_viewmodel.network.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(application: Application) : AndroidViewModel(application){

    val readAllData : LiveData<List<SocialAppUser>>
    private val repository : SocialAppRepository

    init{
        val socialAppDao = SocialAppDatabase.getDatabase(application).userDao()
        repository = SocialAppRepository(socialAppDao)
        readAllData = repository.readAllData
    }


    fun makeApiCall() {
        Log.d("HERE","API CALL !!")
        val apiService = RetroInstance.getRetroClient()?.create(APIService::class.java)
            val call: Call<List<MovieModel>> =
                apiService!!.getMovieList("vIF1Szg9Z0wV4sV20bAgw_1Mxuunm2H9i4nLq8IsJwg")

            call.enqueue(object : Callback<List<MovieModel>> {
                override fun onResponse(
                    call: Call<List<MovieModel>>,
                    response: Response<List<MovieModel>>
                ) {
                    val responses = response.body()
                    val comments = mutableListOf<String>()
                    if (responses != null) {
                        for (singleResponse in responses) {
                            val newUser = SocialAppUser(
                                0, singleResponse.urls?.thumb, 0,
                                comments
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                repository.addUser(newUser)
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                    Log.d("Dummy", "${t.message}")
                }
            })
    }

    fun getMovieListObserver():LiveData<List<SocialAppUser>>{
        return readAllData
    }

    fun updateLikesCount(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLikeCount(id)
        }
    }

    fun updateComment(id:Int,comment:List<String>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateComment(id,comment)
        }
    }



}