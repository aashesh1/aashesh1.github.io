package com.bb2.aasheshkumar.mobilebanking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import android.widget.Button;


public class foodbill extends AppCompatActivity {

    ApplicationDB db;
    private String username;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodbill);

        food = findViewById(R.id.food
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openactivity_food();

                    }
                }));
    }*/
}
