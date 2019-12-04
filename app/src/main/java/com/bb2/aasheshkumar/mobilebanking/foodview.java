package com.bb2.aasheshkumar.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.utility.ApiHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.Response;

public class foodview extends AppCompatActivity {

    ApplicationDB db;
    private String username;

    private RadioButton foodpands,keltrics;
    private TextInputEditText amount;

    private String foodpand = "pandafood07@gmail.com";
    private String keltric = "Kelectric018@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodview);

        amount = (TextInputEditText) findViewById(R.id.amount);
        foodpands = (RadioButton) findViewById(R.id.customer);
        keltrics = (RadioButton) findViewById(R.id.manager);
        db = Room.databaseBuilder(getApplicationContext(),
                ApplicationDB.class, "mobile-banking").allowMainThreadQueries().build();

        username = getIntent().getStringExtra("username");

    }



    public void Transfer(View view){


        Customer fromCustoner= db.customerDao().getCustomer(username);

      if(foodpands.isSelected()){

          String msg = "THe amount of "+Double.parseDouble(amount.getText().toString())+" has been cerdited to you venture by "+fromCustoner.user.fname+" "+fromCustoner.user.lname;
          new EmailTask().execute("https://vast-thicket-60809.herokuapp.com/"+foodpand+"/"+msg);

      }else{
          String msg = "THe amount of "+Double.parseDouble(amount.getText().toString())+" has been cerdited to you vanture by "+fromCustoner.user.fname+" "+fromCustoner.user.lname;
          new EmailTask().execute("https://vast-thicket-60809.herokuapp.com/"+keltric+"/"+msg);
      }


        if(fromCustoner.balance == null)
            fromCustoner.balance = 0.0;

        fromCustoner.balance -=Double.parseDouble(amount.getText().toString());

        db.customerDao().updateAll(fromCustoner);


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
                Toast.makeText(foodview.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }else if(res instanceof Response){
                Response response = (Response) res;
                Toast.makeText(foodview.this, "Email sent.", Toast.LENGTH_SHORT).show();

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
