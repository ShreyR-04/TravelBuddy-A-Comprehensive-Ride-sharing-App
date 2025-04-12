package com.example.madcourseproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.madcourseproject.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        // Add a marker in Pune and move the camera
        LatLng pune = new LatLng(18.52, 73.85);
        myMap.addMarker(new MarkerOptions().position(pune).title("Pune,Maharashtra"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(pune));
        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pune,16f));

        myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                myMap.addMarker(new MarkerOptions().position(latLng).title("Set Location here"));

                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    ArrayList<Address> geocoder1 = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.d("Address",geocoder1.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });



    }
}