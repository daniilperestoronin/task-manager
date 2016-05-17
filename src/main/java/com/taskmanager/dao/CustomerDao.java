package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;

/**
 * Created by perestoronin
 */
public interface CustomerDao {

    int singIn(Customer customer);

    int singUp(Customer customer);
}
