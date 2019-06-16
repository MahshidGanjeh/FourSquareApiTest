package com.tech.foursquareApiTest.ui.venueslist

import android.content.Context
import com.tech.foursquareApiTest.data.VenueRepository
import com.tech.foursquareApiTest.data.model.Venue

class VenuePresenter(val tView: VenueContract.View) : VenueContract.Presenter {

    lateinit var mView: VenueContract.View
    var repo = VenueRepository()


    override fun loadData(context: Context) {

        mView = tView

        repo.fetchVenues(object : VenueRepository.ApiResult {

            override fun onSuccess(list: MutableList<Venue>) {
                mView.showVenues(list)
            }

            override fun onFail() {

            }
        },context)

    }
}