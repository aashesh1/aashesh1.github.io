package com.bb2.aasheshkumar.mobilebanking;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bb2.aasheshkumar.mobilebanking.dao.CustomerDao;
import com.bb2.aasheshkumar.mobilebanking.dao.ManagerDao;
import com.bb2.aasheshkumar.mobilebanking.dao.TransferDao;
import com.bb2.aasheshkumar.mobilebanking.entities.Customer;
import com.bb2.aasheshkumar.mobilebanking.entities.Manager;
import com.bb2.aasheshkumar.mobilebanking.entities.Transfer;

@Database(entities = {Customer.class, Manager.class,  Transfer.class}, version = 1)
public abstract class ApplicationDB extends RoomDatabase {
    public abstract ManagerDao managerDao();

    public abstract CustomerDao customerDao();
    public abstract TransferDao transferDao();



}
