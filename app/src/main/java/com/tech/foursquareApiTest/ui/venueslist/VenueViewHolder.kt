package com.tech.foursquareApiTest.ui.venueslist

import android.support.v7.widget.RecyclerView

import android.view.View
import android.widget.TextView
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Venue
import kotlinx.android.synthetic.main.venue_item.view.*


class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mNameTextView = itemView.venue_name_tv
    var mAddressTextView = itemView.venue_address_tv

    fun onBind(venue: Venue) {
        mNameTextView.setText(venue.getName())
        mAddressTextView.setText(venue.location.address)
    }
}