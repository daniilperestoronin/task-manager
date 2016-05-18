package com.taskmanager.services;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.ProjectScore;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface CustomerService {

    int createNeTechnicalTask(TechnicalTask technicalTask);

    List<ProjectScore> getCustomerScores(Customer customer);

    List<TechnicalTask> getCustomerTecnicalTask(Customer customer);
}
