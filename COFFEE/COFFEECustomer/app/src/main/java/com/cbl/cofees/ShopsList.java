package com.cbl.cofees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.cbl.cofees.ADAPTERS.ItemsAdapter;
import com.cbl.cofees.ADAPTERS.ShopsAdapter;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventLoadVendos;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventVendorSaved;
import com.cbl.cofees.NETWORKING.RequestManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ShopsList extends AppCompatActivity {

    RecyclerView shopsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
        shopsList = findViewById(R.id.shoplist);
        EventBus.getDefault().register(this);
        RequestManager.getInstantiate(this).loadSops();

    }


    @Subscribe
    public void eventExecution(Object event) {
        if (event instanceof EventLoadVendos) {
            ShopsAdapter truckAdapter = new ShopsAdapter(this, ((EventLoadVendos) event).getSendinglist());
            shopsList.setLayoutManager(new LinearLayoutManager(this));
            shopsList.setAdapter(truckAdapter);
            truckAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
