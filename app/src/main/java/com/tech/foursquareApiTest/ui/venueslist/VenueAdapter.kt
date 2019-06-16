package com.tech.foursquareApiTest.ui.venueslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Venue

class VenueAdapter : RecyclerView.Adapter<VenueViewHolder>() {

    private var mVenueList: List<Venue> = listOf()
    private lateinit var mInflater: LayoutInflater

    fun VenueAdapter(inflater: LayoutInflater, list: List<Venue>) {
        mInflater = inflater
        mVenueList = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VenueViewHolder {
        var myView = mInflater.inflate(R.layout.venue_item, p0, false)
        return VenueViewHolder(myView)
    }


    override fun onBindViewHolder(p0: VenueViewHolder, p1: Int) {
        p0.onBind(mVenueList.get(p1))
    }

    override fun getItemCount(): Int {
        return mVenueList.size
    }
}