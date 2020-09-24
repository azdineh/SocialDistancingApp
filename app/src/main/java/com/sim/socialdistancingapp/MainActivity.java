package com.sim.socialdistancingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Socail Distancing App";
    TextInputLayout textCIN;
    MaterialButton buttonLogin;

    List<String> CINs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCIN=findViewById(R.id.textInputLayout);
        buttonLogin=findViewById(R.id.button_login);

        CINs= new ArrayList<>();
        CINs.add("D990618");
        CINs.add("Z760911");
        CINs.add("A909090");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLoginValid(textCIN.getEditText().getText().toString())){
                    textCIN.setError("Login is not valid");
                }
                else {
                    textCIN.setError(null);
                    if(isLoginExist(textCIN.getEditText().getText().toString())){
                        //log in
                        textCIN.setError(null);
                        Intent intent=new Intent(getApplicationContext(),CitizenActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        textCIN.setError("CIN not found");
                    }
                }
            }
        });



    }

    private boolean isLoginValid(String text) {
        return text != null && text.length() > 6 && text.length()<=8 && text!="";
    }

    private boolean isLoginExist(String cin) {
        return CINs.contains(cin);
    }
}