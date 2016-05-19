package com.taskmanager.services;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface CustomerService {

    int createNeTechnicalTask(TechnicalTask technicalTask);

    List<Project> getCustomerScores(Customer customer);

    List<TechnicalTask> getCustomerTecnicalTask(Customer customer);
}
