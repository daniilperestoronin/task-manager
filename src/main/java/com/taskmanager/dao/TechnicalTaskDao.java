package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface TechnicalTaskDao {

    int addTechnicalTask(TechnicalTask technicalTask);

    List<TechnicalTask> getNotDoneTechnicalTask();

    List<TechnicalTask> getCustomerTechnicalTask(Customer customer);

    void updateTechnicalTask(TechnicalTask technicalTask);

    void deleteTechnicalTask(TechnicalTask technicalTask);
}
