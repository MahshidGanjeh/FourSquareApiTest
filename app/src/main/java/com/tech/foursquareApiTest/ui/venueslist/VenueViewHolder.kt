package com.tech.foursquareApiTest.ui.venueslist

import android.support.v7.widget.RecyclerView

import android.view.View
import android.widget.TextView
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Venue


class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mNameTextView: TextView
    lateinit var mAddressTextView: TextView

    fun VenueViewHolder() {
        mNameTextView = itemView.findViewById(R.id.venue_name_tv)
        mAddressTextView = itemView.findViewById(R.id.venue_address_tv)
    }

    fun onBind(venue: Venue) {
        mNameTextView.setText(venue.getName())
        mAddressTextView.setText(venue.location.address)
    }
}