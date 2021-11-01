package com.cbl.cofees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.cbl.cofees.EVENTMANAGMENT.passed.EventVendorSaved;
import com.cbl.cofees.NETWORKING.RequestManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button submitAll;
    Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        submitAll = findViewById(R.id.loginBtn);
        submitAll.setVisibility(View.GONE);




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        lat=getIntent().getExtras().getDouble("lat");
        lon=getIntent().getExtras().getDouble("lon");
        String shopName=getIntent().getExtras().getString("shopname");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).setDraggable(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title(shopName));
//        myMarker.setDraggable(true);
        LatLng position = myMarker.getPosition(); //
//        lat = position.latitude;
//        lon = position.longitude;

    }


    @Subscribe
    public void eventExecution(Object event) {
        if (event instanceof EventVendorSaved) {
            startActivity(new Intent(ViewLocationActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
