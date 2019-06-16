package com.tech.foursquareApiTest.ui.venueslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Venue

class VenueAdapter(val list: MutableList<Venue>, val context: Context) : RecyclerView.Adapter<VenueViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VenueViewHolder {
        var mInflater = LayoutInflater.from(context)
        var myView = mInflater.inflate(R.layout.venue_item, p0, false)
        return VenueViewHolder(myView)
    }


    override fun onBindViewHolder(p0: VenueViewHolder, p1: Int) {
        p0.onBind(list.get(p1))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}