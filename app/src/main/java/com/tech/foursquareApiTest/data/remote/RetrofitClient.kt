package com.tech.foursquareApiTest.data.remote

import retrofit2.Retrofit
import com.tech.foursquareApiTest.data.model.Example
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


public class RetrofitClient {

    private var retrofit: Retrofit? = null
    val BASE_URL = "https://api.foursquare.com/v2/venues/explore?" +
            "ll=40.7,-74&client_id=3TVQHARU45IF4YSJO4U4OUH2IEBWD5AJUZFWW1XHXYNA4XNC" +
            "&client_secret=BSKNTD2PRL5T2QDPSFJWZMIUAKC3DQE5MHEAO3BG1ZDJUJE5&v=20190502"

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}


