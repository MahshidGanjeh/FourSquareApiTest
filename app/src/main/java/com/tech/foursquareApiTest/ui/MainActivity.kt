package com.tech.foursquareApiTest.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SettingsSlicesContract.KEY_LOCATION
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.tech.foursquareApiTest.R
import com.tech.foursquareApiTest.ui.venueslist.VenueFragment

class MainActivity : AppCompatActivity() {

    lateinit var mVenueFragment: VenueFragment
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        var manager = supportFragmentManager
        mVenueFragment = VenueFragment()
        manager
            .beginTransaction()
            .replace(R.id.parent, mVenueFragment)
            .commit()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            mFusedLocationClient?.lastLocation
                ?.addOnSuccessListener(this, object : OnSuccessListener<Location> {
                    override fun onSuccess(p0: Location?) {
                        if (p0 != null) {
                            Toast.makeText(
                                applicationContext,
                                "The lang is" + p0.latitude.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            mVenueFragment.getLastLocation()
                        }

                    }
                })
        }
    }
}


