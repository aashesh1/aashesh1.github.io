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

public class TransferActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextInputEditText amount;
    private TextInputEditText toAccountNumber;
    ApplicationDB db;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        linearLayout = (LinearLayout) findViewById(R.id.main_layout);
        amount = (TextInputEditText) findViewById(R.id.amount);
        toAccountNumber = (TextInputEditText) findViewById(R.id.account_number);

        db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

        username = getIntent().getStringExtra("username");
    }

    public void Transfer(View view) throws IOException {

        if(toAccountNumber.getText().toString().isEmpty()){
            Snackbar.make(linearLayout, getString(R.string.account_failed).toUpperCase(), Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }


        Customer toCusomter = db.customerDao().get(Integer.parseInt(toAccountNumber.getText().toString()));
        Customer fromCustoner= db.customerDao().getCustomer(username);

        if(toCusomter.balance == null)
        toCusomter.balance = 0.0;
        if(fromCustoner.balance == null)
        fromCustoner.balance = 0.0;
        toCusomter.balance += Double.parseDouble(amount.getText().toString());
        fromCustoner.balance -=Double.parseDouble(amount.getText().toString());

        db.customerDao().updateAll(toCusomter,fromCustoner);


    }

}
