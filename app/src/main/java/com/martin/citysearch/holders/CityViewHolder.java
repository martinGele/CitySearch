package com.martin.citysearch.holders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.martin.citysearch.R;
import com.martin.citysearch.main.City;
import com.martin.citysearch.map.OpenMapActivity;

public class CityViewHolder extends RecyclerView.ViewHolder {
    public TextView country, city, longitude, latitude;
    public LinearLayout linearLayoutCity;


    public CityViewHolder(View view) {
        super(view);
        country = view.findViewById(R.id.country);
        city = view.findViewById(R.id.city);
        longitude = view.findViewById(R.id.longitude);
        latitude = view.findViewById(R.id.latitude);
        linearLayoutCity = view.findViewById(R.id.city_layout);


    }

    public void update(final Context context, final City cityModel, final SupportMapFragment mapFragment) {
        country.setText(cityModel.getCountry());
        city.setText(cityModel.getName());
        longitude.setText("Lon: " + cityModel.getCoord().getLon());
        latitude.setText("Lat: " + cityModel.getCoord().getLat());

        Log.d("GetLatLon", String.valueOf(cityModel.getCoord().getLon()));

        linearLayoutCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                    Intent intent = new Intent(context, OpenMapActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("lat", cityModel.getCoord().getLat());
                    b.putDouble("lon", cityModel.getCoord().getLon());
                    intent.putExtras(b);
                    context.startActivity(intent);
                } else {


                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                            LatLng loc = new LatLng(cityModel.getCoord().getLat(), cityModel.getCoord().getLon());
                            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);


                            googleMap.addMarker(new MarkerOptions()
                                    .position(loc)
                                    .title(cityModel.getName())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                            googleMap.animateCamera(zoom);
                        }
                    });

                }
            }
        });


    }
}