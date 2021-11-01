package com.cbl.cofees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterStepTwoActivity extends AppCompatActivity {
Button shopLocaiton;

TextInputEditText shopnametxt,addressonetxt,addresstwotxt,addressthreetxt;

String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shop);
        shopLocaiton=findViewById(R.id.loginBtn);
        user_id=getIntent().getExtras().getString("user_id");
        init();
        shopLocaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWithextra=new Intent(RegisterStepTwoActivity.this,ShopLocationActivity.class);
                intentWithextra.putExtra("sn",shopnametxt.getText().toString());
                intentWithextra.putExtra("a1",addressonetxt.getText().toString());
                intentWithextra.putExtra("a2",addresstwotxt.getText().toString());
                intentWithextra.putExtra("a3",addressthreetxt.getText().toString());
                intentWithextra.putExtra("created_by", user_id);
                startActivity(intentWithextra);
                finish();
            }
        });


    }

    public void init(){
        shopnametxt=findViewById(R.id.userIDTextInputEditText);
        addressonetxt=findViewById(R.id.addressone);
        addresstwotxt=findViewById(R.id.addresstwo);
        addressthreetxt=findViewById(R.id.addressthree);

    }

}
