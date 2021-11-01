package com.cbl.cofees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cbl.cofees.EVENTMANAGMENT.passed.EventRegisterUser;
import com.cbl.cofees.NETWORKING.RequestManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RegisterActivity extends AppCompatActivity {
    Button nextButton;

    TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nextButton = findViewById(R.id.loginBtn);
        username = findViewById(R.id.userIDTextInputEditText);
        password = findViewById(R.id.passwordTextInputEditText);
        EventBus.getDefault().register(this);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObject sendingUser = new JsonObject();
                sendingUser.addProperty("username", username.getText().toString());
                sendingUser.addProperty("password", password.getText().toString());
                sendingUser.addProperty("type", "VENDOR");
                RequestManager.getInstantiate(RegisterActivity.this).registerVendor(sendingUser);


            }
        });

    }

    @Subscribe
    public void eventExecution(Object event) {
        if (event instanceof EventRegisterUser) {
            startActivity(new Intent(RegisterActivity.this, RegisterStepTwoActivity.class).putExtra("user_id",((EventRegisterUser) event).getUser_id()));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
