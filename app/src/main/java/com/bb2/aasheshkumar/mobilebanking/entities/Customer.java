package com.bb2.aasheshkumar.mobilebanking.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {

    @PrimaryKey
    public Integer customerId;
    public String pin;

    public Double balance;

    @Embedded
    public User user;

    public String accountNumber;

}
