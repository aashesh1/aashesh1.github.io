package com.bb2.aasheshkumar.mobilebanking.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bb2.aasheshkumar.mobilebanking.DateConverter;

import java.util.Date;

@Entity
public class Transfer {

    @PrimaryKey
    public Integer transferId;

    public Integer fromAccountId;

    public Integer toAccountId;

    @TypeConverters(DateConverter.class)
    public Date transferDate;

    public Double amount;

}
