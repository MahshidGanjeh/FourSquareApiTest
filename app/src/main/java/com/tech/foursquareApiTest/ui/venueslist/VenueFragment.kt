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

class VenueFragment : Fragment(), VenueContract.View {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPresenter: VenuePresenter
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = VenuePresenter(this)
        mContext = this!!.context!!

        mRecyclerView = view.findViewById(com.tech.foursquareApiTest.R.id.venue_list_recycler)
        mRecyclerView.setLayoutManager(LinearLayoutManager(view.getContext()));

        mPresenter.loadData()

    }

    override fun showVenues(list: MutableList<Venue>) {
        mRecyclerView.adapter = VenueAdapter(list, mContext)
    }
}
