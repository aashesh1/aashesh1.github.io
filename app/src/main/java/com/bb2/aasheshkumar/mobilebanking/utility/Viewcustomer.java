package com.bb2.aasheshkumar.mobilebanking.utility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;

import com.bb2.aasheshkumar.mobilebanking.AdapterCustomer;
import com.bb2.aasheshkumar.mobilebanking.ApplicationDB;
import com.bb2.aasheshkumar.mobilebanking.R;
import com.bb2.aasheshkumar.mobilebanking.entities.Customer;

import java.util.List;

public class Viewcustomer extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcustomer);
        ApplicationDB db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

        listView = (ListView) findViewById(R.id.customes);

        List<Customer> customerList = db.customerDao().getAll();


        AdapterCustomer adbPerson;
        adbPerson= new AdapterCustomer (Viewcustomer.this, 0, customerList);
        listView.setAdapter(adbPerson);

    }
}
