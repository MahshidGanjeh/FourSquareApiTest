package com.tech.foursquareApiTest.ui.venueslist

import com.tech.foursquareApiTest.data.model.Venue

interface VenueContract {

    interface View {
        fun showVenues(list: List<Venue>)
    }

    interface Presenter {
        fun loadData()
    }
}