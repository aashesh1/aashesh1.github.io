package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bb2.aasheshkumar.mobilebanking.utility.Viewcustomer;


public class ManagerActivity extends AppCompatActivity {
 private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        ApplicationDB db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_viewcustomer();
            }
        });
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_addcustomer();
            }
        });
    }
    public void openactivity_addcustomer(){
        Intent intent = new Intent(this,addcustomer.class);
        startActivity(intent);
    }
    public void openactivity_viewcustomer(){
        Intent intent = new Intent(this, Viewcustomer.class);
        startActivity(intent);
    }
}
