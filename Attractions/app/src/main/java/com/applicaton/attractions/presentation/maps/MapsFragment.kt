package com.applicaton.attractions.presentation.maps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.applicaton.attractions.R
import com.applicaton.attractions.app.App
import com.applicaton.attractions.databinding.CustomInfoContentsBinding
import com.applicaton.attractions.databinding.FragmentMapsBinding
import com.applicaton.attractions.presentation.maps.models.FeatureInfoMaps
import com.applicaton.attractions.presentation.maps.models.FeatureMaps
import com.applicaton.attractions.presentation.maps.models.ParcelableMap
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class MapsFragment : Fragment() {

    private var cameraPosition: CameraPosition? = null
    private var isNewLocation: Boolean = false
    private var isRotateCamera: Boolean = false
    private var needMoveCamera = false
    private var locationListener: LocationSource.OnLocationChangedListener? = null
    private var map: GoogleMap? = null
    private var lastKnownLocation: Location? = null
    private val radius = 800
    private var mapOfFeaturesAndInfo = mutableMapOf<FeatureMaps, FeatureInfoMaps>()
    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var binding: FragmentMapsBinding

    private val viewModel: MapsViewModel by activityViewModels {
        (requireContext().applicationContext as App).getAppComponent().mapsViewModelFactory()
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { it }) {
            startLocation()
        }
    }


    private val locationCallback = object : LocationCallback() {
        @SuppressLint("MissingPermission")
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { location ->
                locationListener?.onLocationChanged(location)
                if (needMoveCamera) {
                    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        LatLng(location.latitude, location.longitude),
                        18f
                    )
                    if (cameraPosition != null) {
                        cameraPosition?.let { position ->
                            map?.moveCamera(CameraUpdateFactory.newCameraPosition(position))
                        }
                    } else {
                        map?.moveCamera(cameraUpdate)

                    }
                    needMoveCamera = false
                }

                if (isUserOutsideRadius(location)) {
                    updateMarkers(location)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocation() {
        map?.isMyLocationEnabled = true
        needMoveCamera = true

        val request = LocationRequest.create()
            .setInterval(1_000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)

        fusedClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        if (this.isResumed && isRotateCamera) {
            checkPermissions()
        }
        with(googleMap.uiSettings) {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.combinedFeatureList.collect { list ->
                    if (list.isEmpty()) {
                        if (!isNewLocation) {
                            mapOfFeaturesAndInfo.forEach { (featureMaps, featureMapsInfo) ->
                                val feature = LatLng(
                                        featureMaps.geometry.coordinates[1],
                                        featureMaps.geometry.coordinates[0]
                                )
                                map?.addMarker(
                                    MarkerOptions().position(feature)
                                        .title(featureMaps.properties.name)
                                        .snippet(
                                            """
                                            |Kind: ${
                                                featureMapsInfo.kinds.substringBefore(",")
                                                    .replace('_', ' ', true)
                                            }. Rate: ${featureMapsInfo.rate}
                                            |""".trimMargin()
                                        )
                                )
                            }
                        } else {
                            mapOfFeaturesAndInfo.clear()
                        }
                    } else {
                        mapOfFeaturesAndInfo.clear()
                        list.forEach { (featureMaps, featureMapsInfo) ->
                            val feature = LatLng(
                                featureMaps.geometry.coordinates[1],
                                featureMaps.geometry.coordinates[0]
                            )
                            val marker = map?.addMarker(
                                MarkerOptions().position(feature)
                                    .title(featureMaps.properties.name)
                                    .snippet(
                                        """
                                            |Kind: ${
                                            featureMapsInfo.kinds.substringBefore(",")
                                                .replace('_', ' ', true)
                                        }. Rate: ${featureMapsInfo.rate}
                                            |""".trimMargin()
                                    )
                            )
                            if (marker != null) {
                                mapOfFeaturesAndInfo[featureMaps] = featureMapsInfo
                            }
                        }
                    }
                }
            }
        }

        googleMap.setLocationSource(object : LocationSource {
            override fun activate(p0: LocationSource.OnLocationChangedListener) {
                locationListener = p0
            }

            override fun deactivate() {
                locationListener = null
            }
        })

        map?.setOnMyLocationButtonClickListener {
            val locationManager =
                context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (!isGpsEnabled) {
                Toast.makeText(
                    context,
                    "Необходимо включить геолокацию",
                    Toast.LENGTH_SHORT
                ).show()
                true
            } else {
                false
            }
        }

    }

    private fun isUserOutsideRadius(location: Location): Boolean {
        lastKnownLocation?.let {
            val results = FloatArray(1)
            Location.distanceBetween(
                it.latitude, it.longitude,
                location.latitude, location.longitude,
                results
            )
            return results[0] > radius
        } ?: run {
            lastKnownLocation = location
            return true
        }
    }

    private fun updateMarkers(location: Location) {
        if (isUserOutsideRadius(location) || cameraPosition == null) {
            viewLifecycleOwner.lifecycleScope.launch {
                map?.clear()
                viewModel.getFeatures(lon = location.longitude, lat = location.latitude)
            }
            lastKnownLocation = location
            isNewLocation = true

            viewLifecycleOwner.lifecycleScope.launch {
                delay(3000)
                if (viewModel.state.value is MapsState.Success && viewModel.features.value.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Рядом с вашим местоположением нету достопримечательностей",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            isNewLocation = false
        }
    }


    override fun onResume() {
        super.onResume()

        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(requireContext(), "Геолокация отключена", Toast.LENGTH_SHORT).show()
        }
        if (this.isResumed) {
            map?.let {
                checkPermissions()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        fusedClient = LocationServices.getFusedLocationProviderClient(requireContext())

        savedInstanceState?.let {
            isRotateCamera = it.getBoolean(ARG_PARAM1)
            cameraPosition = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(ARG_PARAM2, CameraPosition::class.java)
            } else it.getParcelable(ARG_PARAM2)
            mapOfFeaturesAndInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                (it.getParcelable(ARG_PARAM3, ParcelableMap::class.java)?.map) ?: mutableMapOf()
            } else {
                (it.getParcelable<ParcelableMap>(ARG_PARAM3)?.map) ?: mutableMapOf()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    if (it is MapsState.Error) {
                        Toast.makeText(requireContext(), "Данные с сервера не были получены", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        map?.let { googleMap ->
            cameraPosition = googleMap.cameraPosition
        }
        fusedClient.removeLocationUpdates(locationCallback)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(ARG_PARAM1, true)
        map?.let { googleMap ->
            cameraPosition = if (cameraPosition == null) null
            else googleMap.cameraPosition
            outState.putParcelable(ARG_PARAM2, cameraPosition)
            outState.putParcelable(ARG_PARAM3, ParcelableMap(mapOfFeaturesAndInfo))

        }
    }

    private fun checkPermissions() {
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocation()
        } else {
            launcher.launch(REQUIRED_PERMISSIONS)
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS: Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}