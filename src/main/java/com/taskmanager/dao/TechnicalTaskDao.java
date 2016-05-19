package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface TechnicalTaskDao {

    int addTechnicalTask(TechnicalTask technicalTask) throws Exception;

    List<TechnicalTask> getNotDoneTechnicalTask() throws Exception;

    List<TechnicalTask> getCustomerTechnicalTask(Customer customer) throws Exception;

    void updateTechnicalTask(TechnicalTask technicalTask) throws Exception;

    void deleteTechnicalTask(TechnicalTask technicalTask) throws Exception;
}
