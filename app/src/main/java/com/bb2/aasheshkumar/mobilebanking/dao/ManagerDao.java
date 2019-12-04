package com.bb2.aasheshkumar.mobilebanking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.bb2.aasheshkumar.mobilebanking.entities.Manager;

@Dao
public interface ManagerDao {

    @Query("SELECT COUNT(managerId) FROM Manager")
    Integer getCount();

    @Query("SELECT * FROM Manager m WHERE m.username = :username LIMIT 1")
    Manager getManager(String username);

    @Insert
    void insertAll(Manager... managers);

    @Delete
    void delete(Manager manager);

}
