package com.example.igift

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.api.Context
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

class MapsFragment : Fragment() {

    /*
    // FusedLocationProviderClient - Main class for receiving location updates.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // LocationRequest - Requirements for the location updates, i.e.,
    // how often you should receive updates, the priority, etc.
    private lateinit var locationRequest: LocationRequest

    // LocationCallback - Called when FusedLocationProviderClient
    // has a new Location
    private lateinit var locationCallback: LocationCallback

    // This will store current location info
    private var currentLocation: Location? = null
    */

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val db = FirebaseFirestore.getInstance()
        db.collection("centrosComerciales")
            .get()
            .addOnSuccessListener { result ->

                //Print Current Location
                /*
                val cliente: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
                locationRequest = LocationRequest().apply {
                    // Sets the desired interval for
                    // active location updates.
                    // This interval is inexact.
                    interval = TimeUnit.SECONDS.toMillis(60)

                    // Sets the fastest rate for active location updates.
                    // This interval is exact, and your application will never
                    // receive updates more frequently than this value
                    fastestInterval = TimeUnit.SECONDS.toMillis(30)

                    // Sets the maximum time when batched location
                    // updates are delivered. Updates may be
                    // delivered sooner than this interval
                    maxWaitTime = TimeUnit.MINUTES.toMillis(2)

                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
                var locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult!!)
                        locationResult?.lastLocation?.let {
                            currentLocation = locationByGps
                            latitude = currentLocation.latitude
                            longitude = currentLocation.longitude
                            // use latitude and longitude as per your need
                        } ?: {
                        }
                    }
                }




                    if(ContextCompat.checkSelfPermission(this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this.requireActivity(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ){
                    //Permisions Granted
                    val locationM : LocationManager = this.requireActivity().getSystemService(android.content.Context.LOCATION_SERVICE) as LocationManager
                    if(locationM.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        locationM.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                        //Location service is ready
                        val complete = OnCompleteListener<> {  }
                        cliente.lastLocation.addOnCompleteListener()

                    }
                }

                */

                for (document in result) {
                    val lat = document.get("coord_y")
                    val lng = document.get("coord_x")
                    val name = document.get("Name").toString()
                    val direccion = document.get("Direccion").toString()
                    val zoom = 10f

                    val centroCom = LatLng(lat as Double, lng as Double)
                    googleMap.addMarker(MarkerOptions()
                        .position(centroCom)
                        .title(name)
                        .snippet(direccion))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroCom,zoom))

                }
                //val bogota = LatLng(74.08175,4.60971)

            }
            .addOnFailureListener { exception ->
                Log.w("Falla:", "Error al traer los datos geolocalizados.", exception)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}