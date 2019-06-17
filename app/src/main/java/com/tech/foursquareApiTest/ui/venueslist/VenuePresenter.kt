package com.tech.foursquareApiTest.ui.venueslist

import android.content.Context
import com.tech.foursquareApiTest.data.VenueRepository
import com.tech.foursquareApiTest.data.model.Venue

class VenuePresenter(val tView: VenueContract.View) : VenueContract.Presenter {

    lateinit var mView: VenueContract.View


    override fun loadData(context: Context, lat: String, lng: String) {

        var repo = VenueRepository(lat, lng)
        mView = tView

        repo.fetchVenues(object : VenueRepository.ApiResult {

            override fun onSuccess(list: MutableList<Venue>) {
                mView.showVenues(list)
            }

            override fun onFail() {

            }
        }, context)

    }
}