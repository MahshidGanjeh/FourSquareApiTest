package com.tech.foursquareApiTest.ui.venueslist


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.tech.foursquareApiTest.data.model.Example
import com.tech.foursquareApiTest.data.model.Venue
import com.tech.foursquareApiTest.data.remote.GetApiService
import com.tech.foursquareApiTest.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.tech.foursquareApiTest.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VenueFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private var mVenueList: MutableList<Venue> = mutableListOf()
    private lateinit var co: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(com.tech.foursquareApiTest.R.id.venue_list_recycler)
        mRecyclerView.setLayoutManager(LinearLayoutManager(view.getContext()));
        co = this!!.context!!


        var retrofit = RetrofitClient().getClient()
        var service = retrofit?.create(GetApiService::class.java)
        var call = service?.getVenues(
            "40.7,-74", "" +
                    "3TVQHARU45IF4YSJO4U4OUH2IEBWD5AJUZFWW1XHXYNA4XNC",
            "BSKNTD2PRL5T2QDPSFJWZMIUAKC3DQE5MHEAO3BG1ZDJUJE5",
            "20190502"
        )

        call?.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.code() == 200) {

                    var num: Int? = response.body()?.response?.totalResults
                    Toast.makeText(activity, num.toString(), Toast.LENGTH_LONG).show()

                    var items = response.body()
                        ?.response
                        ?.groups
                        ?.get(0)
                        ?.items
                    for (i: Int in 0..10) {
                        mVenueList.add(items?.get(i)?.venue!!)
                    }

                    mRecyclerView.adapter = VenueAdapter(mVenueList, co)

                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
            }
        })

    }
}
