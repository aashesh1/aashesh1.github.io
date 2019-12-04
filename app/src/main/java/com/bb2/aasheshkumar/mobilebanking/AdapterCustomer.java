package com.bb2.aasheshkumar.mobilebanking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterCustomer extends ArrayAdapter<Customer> {

    private List<Customer> objtecs;
    private static LayoutInflater inflater = null;

    public AdapterCustomer(@NonNull Context context, int resource, @NonNull List<Customer> objects) {
        super(context, resource, objects);
        this.objtecs = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return objtecs.size();
    }

    public Customer getItem(Integer position) {
        return objtecs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView u_name;
        public TextView display_name;
        public TextView display_number;
        public TextView phoneNumber;
        public TextView cnicnumber;
        public TextView email;
        public TextView blance;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.layout_customers, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.title);
                holder.display_number = (TextView) vi.findViewById(R.id.description);
                holder.u_name = (TextView) vi.findViewById(R.id.u_name);
                holder.email=(TextView) vi.findViewById(R.id.email);

holder.phoneNumber = (TextView) vi.findViewById(R.id.phone);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }
            holder.cnicnumber = (TextView) vi.findViewById(R.id.cnicnumber);

            holder.u_name.setText(objtecs.get(position).user.username);
            holder.display_name.setText(objtecs.get(position).user.fname);
            holder.display_number.setText(objtecs.get(position).user.lname);
            holder.phoneNumber.setText(objtecs.get(position).user.phoneNumber);
            holder.cnicnumber.setText(objtecs.get(position).user.cnic);
            holder.email.setText(objtecs.get(position).user.email);
            holder.email.setText(objtecs.get(position).user.email);

        } catch (Exception e) {


        }
        return vi;
    }

}
