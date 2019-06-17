package com.tech.foursquareApiTest.ui.venueslist

import android.content.Context
import com.tech.foursquareApiTest.data.model.Venue

interface VenueContract {

    interface View {
        fun showVenues(list: MutableList<Venue>)
    }

    interface Presenter {
        fun loadData(context: Context, lat: String, lng: String)
    }
}