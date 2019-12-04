package com.bb2.aasheshkumar.mobilebanking.entities;

import androidx.room.Embedded;
import androidx.room.TypeConverters;

import com.bb2.aasheshkumar.mobilebanking.DateConverter;

import java.util.Date;

public class User {
    public String username;
    public String fname;
    public String lname;
    public Double age;
    public String cnic;
    public String password;
    public String email;
    public String nationality;
    public Double salary;
    @TypeConverters(DateConverter.class)
    public Date dob;
    public String phoneNumber;
    public String gender;
    @Embedded
    public Address address;

}
