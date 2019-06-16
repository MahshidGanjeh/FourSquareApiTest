package com.tech.foursquareApiTest.ui.venueslist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Example
import com.tech.foursquareApiTest.data.remote.GetApiService
import com.tech.foursquareApiTest.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VenueFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.venue_list_recycler)

        var retrofit = RetrofitClient().getClient()
        var service = retrofit?.create(GetApiService::class.java)
        var call = service?.getVenues()

        call?.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.code() == 200) {
                    var name = response.body()
                        ?.response
                        ?.groups
                        ?.get(0)
                        ?.items
                        ?.get(0)
                        ?.venue
                        ?.name

                    Toast.makeText(activity, name, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
            }
        })
    }
}
