package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;

/**
 * Created by perestoronin
 */
public interface CustomerDao {

    int singIn(Customer customer) throws Exception;

    int singUp(Customer customer) throws Exception;
}
