package com.tech.foursquareApiTest.data

import android.widget.Toast
import com.tech.foursquareApiTest.data.model.Example
import com.tech.foursquareApiTest.data.model.Venue
import com.tech.foursquareApiTest.data.remote.GetApiService
import com.tech.foursquareApiTest.data.remote.RetrofitClient
import com.tech.foursquareApiTest.ui.venueslist.VenueAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenueRepository {

    var mVenueList: MutableList<Venue> = mutableListOf()
    var CLIENT_ID = "3TVQHARU45IF4YSJO4U4OUH2IEBWD5AJUZFWW1XHXYNA4XNC";
    var CLIENT_SECRET = "BSKNTD2PRL5T2QDPSFJWZMIUAKC3DQE5MHEAO3BG1ZDJUJE5"

    fun fetchVenues(callback: ApiResult) {

        var retrofit = RetrofitClient().getClient()
        var service = retrofit?.create(GetApiService::class.java)

        var call = service?.getVenues(
            "40.7,-74",
            CLIENT_ID,
            CLIENT_SECRET,
            "20190502"
        )

        call?.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.code() == 200) {

                    var num: Int? = response.body()?.response?.totalResults

                    var items = response.body()
                        ?.response
                        ?.groups
                        ?.get(0)
                        ?.items
                    for (i: Int in 0..10) {
                        mVenueList.add(items?.get(i)?.venue!!)
                    }

                    callback.onSuccess(mVenueList)
                }

            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                callback.onFail()
            }
        })


    }


    interface ApiResult {
        fun onSuccess(list: MutableList<Venue>)

        fun onFail()
    }

}