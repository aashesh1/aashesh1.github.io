package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.utility.ApiHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

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

        String msg = "THe amount of "+Double.parseDouble(amount.getText().toString())+" has been cerdited to you account by "+fromCustoner.user.fname+" "+fromCustoner.user.lname;
        new EmailTask().execute("https://vast-thicket-60809.herokuapp.com/"+toCusomter.user.email+"/"+msg);

        if(toCusomter.balance == null)
        toCusomter.balance = 0.0;
        if(fromCustoner.balance == null)
        fromCustoner.balance = 0.0;
        toCusomter.balance += Double.parseDouble(amount.getText().toString());
        fromCustoner.balance -=Double.parseDouble(amount.getText().toString());

        db.customerDao().updateAll(toCusomter,fromCustoner);


    }


    private class EmailTask extends AsyncTask<String,Void,Object> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object res) {
            super.onPostExecute(res);

            if(res instanceof IOException){
                IOException e = (IOException) res;
                Toast.makeText(TransferActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }else if(res instanceof Response){
                Response response = (Response) res;
                Toast.makeText(TransferActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        protected Object doInBackground(String... args) {
            Response res = null;
            try {
                res = ApiHelper.getContent(args[0]);
                return res;
            }catch (IOException ioException){
                return ioException;
            }
        }
    }

}
