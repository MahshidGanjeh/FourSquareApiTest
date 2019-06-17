package com.tech.foursquareApiTest.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tech.foursquareApiTest.ui.venueslist.VenueFragment
import com.tech.foursquareApiTest.R


class MainActivity : AppCompatActivity() {

    lateinit var mVenueFragment: VenueFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var manager = supportFragmentManager
        mVenueFragment = VenueFragment()
        manager
            .beginTransaction()
            .replace(com.tech.foursquareApiTest.R.id.parent, mVenueFragment)
            .commit()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) { mVenueFragment.getLastLocation()

        }
    }
}


