package com.bb2.aasheshkumar.mobilebanking;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.bb2.aasheshkumar.mobilebanking.dao.CustomerDao;
import com.bb2.aasheshkumar.mobilebanking.dao.ManagerDao;
import com.bb2.aasheshkumar.mobilebanking.dao.TransferDao;

class ApplicationDBImpl extends ApplicationDB {
    @Override
    public ManagerDao managerDao() {
        return null;
    }



    @Override
    public CustomerDao customerDao() {
        return null;
    }

    @Override
    public TransferDao transferDao() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
