package com.tech.foursquareApiTest.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SettingsSlicesContract.KEY_LOCATION
import android.support.v4.app.ActivityCompat
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


    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1000
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null
    private var mLatitudeLabel: String? = ""
    private var mLongitudeLabel: String? = ""
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    var permissionStrings = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mLastLocation = savedInstanceState.getParcelable(KEY_LOCATION);

        }


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000) // 10 seconds
        locationRequest.setFastestInterval(5 * 1000) // 5 seconds


        if (ActivityCompat.checkSelfPermission(
                this, permissionStrings[0]
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this, permissionStrings[1]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request for permission
            ActivityCompat.requestPermissions(
                this, permissionStrings,
                REQUEST_PERMISSIONS_REQUEST_CODE
            )

        } else {
            // already permission granted
            //getLastLocation()
        }

           var manager = supportFragmentManager
           var venueFragment = VenueFragment()
           manager
               .beginTransaction()
               .replace(R.id.parent, venueFragment)
               .commit()

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
                  //  getLastLocation()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient?.lastLocation
            ?.addOnSuccessListener(this, object : OnSuccessListener<Location> {
                override fun onSuccess(p0: Location?) {
                    mLastLocation = p0
                    mLatitudeLabel = mLastLocation?.latitude.toString()
                    mLongitudeLabel = mLastLocation?.longitude.toString()
                    Toast.makeText(applicationContext, mLongitudeLabel, Toast.LENGTH_SHORT).show();

                    //Toast.makeText(applicationContext, p0.exception.toString(), Toast.LENGTH_SHORT).show();

                }
            })

    }
}
