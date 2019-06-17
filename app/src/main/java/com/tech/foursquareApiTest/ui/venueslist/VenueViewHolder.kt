package com.tech.foursquareApiTest.ui.venueslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.data.model.Venue
import kotlinx.android.synthetic.main.venue_item.view.*


class VenueViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    var mNameTextView = itemView.venue_name_tv
    var mAddressTextView = itemView.venue_address_tv
    var mDistanceTextView = itemView.venue_distance_tv
    var mIconImageView = itemView.venue_icon_imgv

    fun onBind(venue: Venue) {
        mNameTextView.setText(venue.getName())
        mAddressTextView.setText(venue.location.address)
        mDistanceTextView.setText(venue.location.distance.toString() + " km")
        var iconPath = venue.categories.get(0).icon.prefix + "bg_64" +
                venue.categories.get(0).icon.suffix
        Log.d("TTT", iconPath)
        Glide.with(context)
            .load(iconPath)
            .into(mIconImageView);

    }
}