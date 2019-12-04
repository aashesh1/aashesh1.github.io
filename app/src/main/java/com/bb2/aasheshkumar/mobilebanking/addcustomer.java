package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bb2.aasheshkumar.mobilebanking.entities.Address;
import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.entities.Manager;
import com.bb2.aasheshkumar.mobilebanking.entities.User;
import com.bb2.aasheshkumar.mobilebanking.enums.GenderEnum;
import com.bb2.aasheshkumar.mobilebanking.utility.Hashing;

import java.security.NoSuchAlgorithmException;

public class addcustomer extends AppCompatActivity {

    EditText edituname,editname,editlanem,editcnic,editpass,editage,editemail,editphone,editablance;
    Button button;

    ApplicationDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

    edituname=(EditText)findViewById(R.id.username);
        editname = (EditText)findViewById(R.id.name);
        editlanem = (EditText)findViewById(R.id.lname);
        editcnic = (EditText)findViewById(R.id.cnic);
        editpass = (EditText)findViewById(R.id.pass);
        editage = (EditText)findViewById(R.id.age);
        editemail = (EditText)findViewById(R.id.email);
        editphone = (EditText)findViewById(R.id.phone);
      editablance= (EditText)findViewById(R.id.ablance);
    }

    public void Save(View view){
        User user = new User();
        Customer customer = new Customer();
        customer.user = user;
        //Integer customerCount = db.customerDao().getCount1();
        user.username=edituname.getText().toString();
        user.fname = editname.getText().toString();
        user.lname = editlanem.getText().toString();
        user.cnic=editcnic.getText().toString();
        try {
            user.password = Hashing.hash(editpass.getText().toString());
        } catch (NoSuchAlgorithmException e) {
            user.password = editpass.getText().toString();
        }
        //user.age = editage.getText().
        user.email=editemail.getText().toString();
        user.phoneNumber=editphone.getText().toString();
customer.balance= Double.parseDouble(editablance.getText().toString());


        db.customerDao().insertAll(customer);
    }
}
