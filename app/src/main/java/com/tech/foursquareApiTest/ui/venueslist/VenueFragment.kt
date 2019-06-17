package com.tech.foursquareApiTest.ui.venueslist


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

import com.tech.foursquareApiTest.data.model.Example
import com.tech.foursquareApiTest.data.model.Venue
import com.tech.foursquareApiTest.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VenueFragment : Fragment(), VenueContract.View {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPresenter: VenuePresenter
    private lateinit var mContext: Context

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1000
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null
    private var mLatitudeLabel: String? = ""
    private var mLongitudeLabel: String? = ""
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    var isContinue = false
    var sGPS = false

    var permissionStrings = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )


    override fun onStart() {
        super.onStart()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)


        locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000) // 10 seconds
        locationRequest.setFastestInterval(5 * 1000) // 5 seconds


        if (ActivityCompat.checkSelfPermission(
                activity!!, permissionStrings[0]
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                activity!!, permissionStrings[1]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request for permission
            ActivityCompat.requestPermissions(
                activity!!, permissionStrings,
                REQUEST_PERMISSIONS_REQUEST_CODE
            )

        } else {
            // already permission granted
            getLastLocation()
        }
    }

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

        mPresenter.loadData(this!!.activity!!)

    }

    override fun showVenues(list: MutableList<Venue>) {
        mRecyclerView.adapter = VenueAdapter(list, mContext)
    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1000 -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    getLastLocation()
                } else {
                    Toast.makeText(activity!!, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @SuppressLint("MissingPermission")

    private fun getLastLocation() {
        mFusedLocationClient?.lastLocation
            ?.addOnCompleteListener(activity!!, object : OnCompleteListener<Location> {
                override fun onComplete(p0: Task<Location>) {
                    if (p0.isSuccessful && p0.result != null) {
                        mLastLocation = p0.result
                        mLatitudeLabel = mLastLocation?.latitude.toString()
                        mLongitudeLabel = mLastLocation?.longitude.toString()
                        Toast.makeText(activity!!, mLongitudeLabel, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(activity!!, p0.exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            })

    }
}
