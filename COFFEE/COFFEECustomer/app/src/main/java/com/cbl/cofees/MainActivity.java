package com.cbl.cofees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cbl.cofees.EVENTMANAGMENT.passed.EventLoginSuccess;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventRegisterUser;
import com.cbl.cofees.NETWORKING.RequestManager;
import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    TextView createAccount;
    TextInputEditText userId;
    TextInputEditText password;
    Button loginButn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (TextUtils.isEmpty(new SystemPreferances(this).getServerURL())) {
            setUrlTOapp();
        }
        EventBus.getDefault().register(this);
        userId=findViewById(R.id.userIDTextInputEditText);
        password=findViewById(R.id.passwordTextInputEditText);
        loginButn=findViewById(R.id.loginBtn);

        createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });


        loginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject sendingObject = new JsonObject();
                sendingObject.addProperty("username", userId.getText().toString());
                sendingObject.addProperty("password", password.getText().toString());
                RequestManager.getInstantiate(MainActivity.this).loginUser(sendingObject);
            }
        });


    }

    AlertDialog templateAlerts;

    public void setUrlTOapp() {

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.url_dialog, null);
        final Button save;
        ImageButton crossbow;
        final EditText url;
        crossbow = view.findViewById(R.id.crossbow);

        url = view.findViewById(R.id.passwordTextInputEditText);
        crossbow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                templateAlerts.dismiss();
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
        if (event instanceof EventLoginSuccess) {
            startActivity(new Intent(MainActivity.this, ShopsList.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
