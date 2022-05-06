package com.example.igift

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.api.Context
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest
import kotlin.math.sqrt

class MapsFragment : Fragment() {

    private val LOCATION_PERMISSION_REQUEST = 1
    private lateinit var mMap : GoogleMap

    private fun getLocationAccess(){

        //Pintar la ubicación actual
        if (ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
        else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                mMap.isMyLocationEnabled = true
            }
            else {
                Toast.makeText(requireContext(), "User has not granted location access permission", Toast.LENGTH_LONG).show()
                //finish()
            }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->

        mMap = googleMap
        getLocationAccess()

        /*
        //Current location
        fusedLocationProviderClient.lastlocation.addOnCompleListener(this){

        }

        mMap.addCircle(
            CircleOptions()
                .center()
        )

         */
        //Pintar Centros Comerciales
        val db = FirebaseFirestore.getInstance()
        db.collection("centrosComerciales")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var lat = document.get("coord_y")
                    var lng = document.get("coord_x")
                    val name = document.get("Name").toString()
                    val direccion = document.get("Direccion").toString()
                    val zoom = 10f
                    lat = lat as Double
                    lng = lng as Double
                    val dist = sqrt(lat*lat+lng*lng)
                    Log.v("DIST",name + dist.toString())
                    val centroCom = LatLng(lat, lng )
                    googleMap.addMarker(MarkerOptions()
                        .position(centroCom)
                        .title(name)
                        .snippet(direccion))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroCom,zoom))
                }
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