package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.entities.Manager;
import com.bb2.aasheshkumar.mobilebanking.utility.Hashing;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private RadioButton manager;
    private LinearLayout linearLayout;
    public boolean isManager = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        manager = (RadioButton) findViewById(R.id.manager);
        linearLayout = (LinearLayout) findViewById(R.id.main_layout);
    }

    public void Login(View view) {

        String loginId = username.getText().toString();
        String userPassword = password.getText().toString();

        //Toast.makeText(this, ""+loginId, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+userPassword, Toast.LENGTH_SHORT).show();

        ApplicationDB db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();
        Toast.makeText(this, " ERRORS"+isManager, Toast.LENGTH_SHORT).show();
        if (isManager) {

            Manager manager = db.managerDao().getManager(loginId);
            if (manager == null) {
                Snackbar.make(linearLayout, getString(R.string.login_failed).toUpperCase(), Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }

            boolean verified = false;

            try {
                if (Hashing.match(manager.user.password, userPassword)) {
                    verified = true;
                }
            } catch (NoSuchAlgorithmException e) {
                if (manager.user.password.equals(userPassword)) {
                    verified = true;
                }
            }

            if (verified) {

                SharedPreferences sharedPreferences = getSharedPreferences("mobile-banking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                boolean login = sharedPreferences.getBoolean("login", false);

                editor.putBoolean("login", true);
                editor.putBoolean("manager", true);
                editor.putInt("id", manager.managerId);
                editor.commit();

                boolean manager1 = sharedPreferences.getBoolean("manager",false);
                System.out.println("Is maangerc "+isManager);
                        if (isManager) {
                            Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                            startActivity(intent);
                        }
                         else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }


                /*Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                startActivity(intent);*/

            } else {
                Snackbar.make(linearLayout, getString(R.string.login_failed).toUpperCase(), Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }

        } else {
            Customer customer = db.customerDao().getCustomer(loginId);

            if (customer == null) {
                Snackbar.make(linearLayout, getString(R.string.login_failed).toUpperCase(), Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }

            boolean verified = false;

            try {
                if (Hashing.match(customer.user.password, userPassword)) {
                    verified = true;
                }
            } catch (NoSuchAlgorithmException e) {
                if (customer.user.password.equals(userPassword)) {
                    verified = true;
                }
            }
            System.out.println("verfied satatus");
            System.out.println(verified);
            if (verified) {

                SharedPreferences sharedPreferences = getSharedPreferences("mobile-banking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("login", true);
                editor.putBoolean("manager", false);
                editor.putInt("id", customer.customerId);
                editor.commit();
                boolean login = sharedPreferences.getBoolean("login", false);
                if(!isManager)
                {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("username",loginId);
                    startActivity(intent);
                }
            }
            else {
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);

            }

                       }

        }

    public void onRadioButtonClicked(View view) {
        System.out.println("i am here");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        Toast.makeText(this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();
            if(radioButton.getText().equals("Manager")){
                isManager=true;
            }
            else {
                isManager = false;
            }
    }
}




            /*else{
                Snackbar.make(linearLayout, getString(R.string.login_failed).toUpperCase(), Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }

        }

    }
}
*/