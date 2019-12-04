package com.bb2.aasheshkumar.mobilebanking.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bb2.aasheshkumar.mobilebanking.entities.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT COUNT(customerId) FROM customer")
    public Integer getCount1();

    @Query("SELECT * FROM Customer c WHERE c.username = :username LIMIT 1")
    public Customer getCustomer(String username);

    @Query("SELECT * FROM Customer c")
    public List<Customer> getAll();

    @Insert
    public void insertAll(Customer... customers);

    @Query("SELECT * FROM Customer c WHERE c.customerId = :id")
    public Customer get(Integer id);

    @Update
    public void updateAll(Customer... customers);

}
