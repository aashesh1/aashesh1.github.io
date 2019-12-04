package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;

import org.w3c.dom.Text;


public class accountview extends AppCompatActivity {

    private String username;
    TextView fname;
    TextView lname;
    TextView blance;
    TextView customerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountview);
        ApplicationDB db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();


        fname = (TextView) findViewById(R.id.fname);
        lname= (TextView) findViewById(R.id.lanme);
        customerid=(TextView) findViewById(R.id.customerid);
        blance = (TextView) findViewById(R.id.blance);

        username = getIntent().getStringExtra("username");

        Customer customer = db.customerDao().getCustomer(username);

        fname.setText(customer.user.fname);
        lname.setText(customer.user.lname);
        customerid.setText(customer.customerId+"");
        blance.setText(customer.balance+"");



    }
}
