package com.cbl.cofees;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cbl.cofees.EVENTMANAGMENT.passed.EventRegisterUser;
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

public class ShopLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button submitAll;
    String shopName, addressaone, addresstwo, addressthree, createdby;
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
        EventBus.getDefault().register(this);
//        shopName = getIntent().getExtras().getString("sn");
//        addressaone = getIntent().getExtras().getString("a1");
//        addresstwo = getIntent().getExtras().getString("a2");
//        addressthree = getIntent().getExtras().getString("a3");
        createdby = getIntent().getExtras().getString("created_by");


        submitAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lat != 0.0 && lon != 0.0) {
                    JsonObject sending = new JsonObject();
                    sending.addProperty("sn", "n/a");
                    sending.addProperty("a1", "n/a");
                    sending.addProperty("a2", "n/a");
                    sending.addProperty("a3", "n/a");
                    sending.addProperty("lat", lat);
                    sending.addProperty("lon", lon);
                    if (createdby != null) {
                        sending.addProperty("created_by", createdby);
                    } else {
                        sending.addProperty("created_by", 0);
                    }


                    RequestManager.getInstantiate(ShopLocationActivity.this).registerVendorStepTwo(sending);


                } else {

                }

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).setDraggable(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMarker.setDraggable(true);
        LatLng position = myMarker.getPosition(); //
        lat = position.latitude;
        lon = position.longitude;

    }


    @Subscribe
    public void eventExecution(Object event) {
        if (event instanceof EventVendorSaved) {
            startActivity(new Intent(ShopLocationActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
