package com.tech.foursquareApiTest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.tech.foursquareApiTest.ui.venueslist.VenueFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var manager = supportFragmentManager
        var venue = VenueFragment()
        manager
            .beginTransaction()
            .replace(R.id.parent, venue)
            .commit()

    }
}
