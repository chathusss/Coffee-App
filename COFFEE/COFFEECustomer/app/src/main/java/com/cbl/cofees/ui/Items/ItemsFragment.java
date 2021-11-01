package com.cbl.cofees.ui.Items;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbl.cofees.ADAPTERS.ItemsAdapter;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventMenuRegisterd;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventRegisterUser;
import com.cbl.cofees.NETWORKING.RequestManager;
import com.cbl.cofees.R;
import com.cbl.cofees.RegisterActivity;
import com.cbl.cofees.RegisterStepTwoActivity;
import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    private ItemsViewModel itemsViewModel;
    RecyclerView itemList;
    Button addMenu;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        itemsViewModel =
                ViewModelProviders.of(this).get(ItemsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        addMenu=root.findViewById(R.id.additems);
        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMenue();
            }
        });
        itemList=root.findViewById(R.id.itemlist);

        itemsViewModel.getText().observe(this, new Observer<ArrayList<JsonObject>>() {

            @Override
            public void onChanged(ArrayList<JsonObject> jsonObjects) {
                ItemsAdapter truckAdapter = new ItemsAdapter(getActivity(),jsonObjects,getActivity().getSupportFragmentManager());
                itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
                itemList.setAdapter(truckAdapter);
                truckAdapter.notifyDataSetChanged();
            }
        });

        EventBus.getDefault().register(this);
        return root;
    }



    AlertDialog templateAlerts;

    public void createMenue() {

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.create_menu, null);
        final Button save;
        ImageButton crossbow;
        final EditText createMenue;
        crossbow = view.findViewById(R.id.crossbow);

        createMenue = view.findViewById(R.id.menuname);
        save=view.findViewById(R.id.loginBtn);
        crossbow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                templateAlerts.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject sendingObj=new JsonObject();
                sendingObj.addProperty("shopid",new SystemPreferances(getActivity()).ShopID());
                sendingObj.addProperty("menuename",createMenue.getText().toString());
                sendingObj.addProperty("is_valid",1);

                RequestManager.getInstantiate(getActivity()).createMenue(sendingObj);
            }
        });



        builder1.setView(view);
        builder1.setCancelable(true);
        templateAlerts = builder1.create();
        templateAlerts.setCancelable(false);
        templateAlerts.show();
    }


    @Subscribe
    public void eventExecution(Object event) {
        if (event instanceof EventMenuRegisterd) {
            templateAlerts.dismiss();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}