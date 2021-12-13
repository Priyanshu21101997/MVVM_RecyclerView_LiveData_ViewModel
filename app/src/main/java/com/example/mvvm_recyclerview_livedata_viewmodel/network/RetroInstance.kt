package com.example.mvvm_recyclerview_livedata_viewmodel.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        private const val BASE_URL = "https://api.unsplash.com/"
        var retrofit: Retrofit? = null

        @JvmStatic
        fun getRetroClient():Retrofit? {

            if(retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)  // This line added for logging
                    .build()
            }

            return retrofit
        }
    }
}