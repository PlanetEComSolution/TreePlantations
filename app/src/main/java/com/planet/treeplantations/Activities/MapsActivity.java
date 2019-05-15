package com.planet.treeplantations.Activities;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.planet.treeplantations.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    Double number = 0.0;
    SupportMapFragment mapFragment;
    String nResult,Degrees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Location");
        String lat = getIntent().getStringExtra("lat");
        String longi = getIntent().getStringExtra("long");
        String input = lat.replaceAll("[^A-Za-z0-9]", " ");
        String input1 = longi.replaceAll("[^A-Za-z0-9]", " ");
        String[] array = input.split(" ");
        String[] array1 = input1.split(" ");

        try{
        int nDegree = Integer.parseInt(array[0]);
        int nMinute = Integer.parseInt(array[1]);
        int nSecond = Integer.parseInt(array[2]);

        int nDegree1 = Integer.parseInt(array1[0]);
        int nMinute1 = Integer.parseInt(array1[1]);
        int nSecond1 = Integer.parseInt(array1[2]);

        double nDegrees = nDegree + (double) nMinute/60 + (double) nSecond/3600;
        double eDegrees = nDegree1 + (double) nMinute1/60 + (double) nSecond1/3600;
        nResult = Double.toString(nDegrees).substring(0,10);
        Degrees = Double.toString(eDegrees).substring(0,10);
        }catch (Exception e){}

        Toast.makeText(this, nResult, Toast.LENGTH_LONG).show();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        fab.setVisibility(View.GONE);
                        fab1.setVisibility(View.VISIBLE);

                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)))
                                .title("Location")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)), 16F));
                    }
                });
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        fab.setVisibility(View.VISIBLE);
                        fab1.setVisibility(View.GONE);
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)))
                                .title("Location")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)), 12F));
                    }
                });
            }
        });

        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)))
                .title("Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(nResult), Double.valueOf(Degrees)), 10F));

    }


}
