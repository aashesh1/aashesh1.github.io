package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bb2.aasheshkumar.mobilebanking.entities.Address;
import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.entities.Manager;
import com.bb2.aasheshkumar.mobilebanking.entities.User;
import com.bb2.aasheshkumar.mobilebanking.enums.GenderEnum;
import com.bb2.aasheshkumar.mobilebanking.utility.Hashing;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationDB db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

        Integer managerCount = db.managerDao().getCount();

        if(managerCount==0){
            Address address = new Address();
            address.city = "Karachi";
            address.state = "Sindh";
            address.street = "Shah Latif Town";

            User user = new User();
            user.address = address;
            user.cnic = "43202-5680128-5";
            user.email = "aashesh.kumar@outlook.com";
            user.fname = "aashesh";
            user.lname = "kumar";
            user.phoneNumber = "0310-2756530";

            try {
                user.password = Hashing.hash("nuces");
            }catch (NoSuchAlgorithmException e){
                user.password = "nuces";
            }

            user.username = "aashesh.k";
            user.gender = GenderEnum.MALE.getAccountEnumId();
            user.nationality = "Pakistani";
            user.salary = 39981.5;
            user.dob  = null;
            user.age = null;

            Manager manager = new Manager();
            manager.user = user;

            db.managerDao().insertAll(manager);
        }

        Integer customerCount = db.customerDao().getCount1();

        if(customerCount==0){
            Address address = new Address();
            address.city = "Karachi";
            address.state = "Sindh";
            address.street = "Shah Latif Town";

            User user = new User();
            user.address = address;
            user.cnic = "43202-5680128-5";
            user.email = "fahad.khan@outlook.com";
            user.fname = "food";
            user.lname = "pandha";
            user.phoneNumber = "0310-2756530";

            try {
                user.password = Hashing.hash("pakistan");
            }catch (NoSuchAlgorithmException e){
                user.password = "pakistan";
            }

            user.username = "fahad.khan";
            user.gender = GenderEnum.MALE.getAccountEnumId();
            user.nationality = "Pakistani";
            user.salary = 39981.5;
            user.dob  = null;
            user.age = null;

            Customer customer = new Customer();
            customer.user = user;

            db.customerDao().insertAll(customer);
        }
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
       /* SharedPreferences sharedPreferences = getSharedPreferences("mobile-banking", Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login",false);

        if(login){

            boolean manager = sharedPreferences.getBoolean("manager",false);
            System.out.println("aashish");
            System.out.println(manager);
            if(manager){
                Intent intent = new Intent(MainActivity.this,ManagerActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }

        }else{
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }*/

    }
}
