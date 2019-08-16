package com.cs360.ryansantos.localcoffeeshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UiSettings mapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapSettings = mMap.getUiSettings();

        mapSettings.setZoomControlsEnabled(true);

        // Add a marker for SNHU and move the camera
        LatLng snhu = new LatLng(43.042190, -71.452117);
        mMap.addMarker(new MarkerOptions().position(snhu).title("Marker for Local Coffee Shop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(snhu, (float) 17.0));
    }
}
