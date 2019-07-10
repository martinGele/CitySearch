package com.martin.citysearch.map;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.martin.citysearch.R;

public class OpenMapActivity extends AppCompatActivity implements OnMapReadyCallback, OpenMap.View {

    private SupportMapFragment mapFragment;
    private double lon, lat;
    private Button buttonAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_open_map);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonAbout = findViewById(R.id.buttonAbout);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_activity);
        mapFragment.getMapAsync(this);

        final OpenMapPresenterImpl openMapPresenter = new OpenMapPresenterImpl(this);


        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapPresenter.openAboutActivity(OpenMapActivity.this);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lon = extras.getDouble("lon");
            lat = extras.getDouble("lat");
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        LatLng loc = new LatLng(lat, lon);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(4);
        googleMap.addMarker(new MarkerOptions()
                .position(loc)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.animateCamera(zoom);


    }


    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
