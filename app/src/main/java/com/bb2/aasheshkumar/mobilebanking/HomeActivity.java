package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bb2.aasheshkumar.mobilebanking.utility.Viewcustomer;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {

    private MaterialCardView transfer;
    private MaterialCardView transaction;
    private MaterialCardView logout;
    private String username;
    private MaterialCardView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        transfer = (MaterialCardView) findViewById(R.id.transfer);
        transaction = (MaterialCardView) findViewById(R.id.transaction);
        logout = (MaterialCardView) findViewById(R.id.logout);
        account = (MaterialCardView) findViewById(R.id.account);

        username = getIntent().getStringExtra("username");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("mobile-banking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("login");
                editor.remove("manager");
                editor.remove("id");
                editor.commit();
                finish();
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,foodbill.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,TransferActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,accountview.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });



        //account.setOnContextClickListener((View)




    }
}
