package com.tech.a7030.data.remote

import retrofit2.Retrofit
import android.R.attr.data
import retrofit2.Call
import retrofit2.http.GET





class RetrofitClient {

    private var retrofit: Retrofit? = null
    val BASE_URL = "https://api.foursquare.com/v2/venues/explore"

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}

interface RetrofitInterface {
    @GET("")
    fun getUsers(): Call<List<>>
}
