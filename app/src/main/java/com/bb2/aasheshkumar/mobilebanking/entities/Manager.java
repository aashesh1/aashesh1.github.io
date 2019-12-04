package com.bb2.aasheshkumar.mobilebanking.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Manager {

    @PrimaryKey
    public Integer managerId;

    @Embedded
    public User user;
}
