package com.taskmanager.services.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectScore;
import com.taskmanager.model.technicaltask.TechnicalTask;
import com.taskmanager.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TechnicalTaskDao technicalTaskDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public int createNeTechnicalTask(TechnicalTask technicalTask) {
        return technicalTaskDao.addTechnicalTask(technicalTask);
    }

    @Override
    public List<ProjectScore> getCustomerScores(Customer customer) {
        List<Project> projectList = projectDao.getCustomerProject(customer);
        List<ProjectScore> projectScores = new LinkedList<>();
        projectList.forEach(project -> projectScores.add(project.getProjectScore()));
        return projectScores;
    }

    @Override
    public List<TechnicalTask> getUndoneTecnicalTask(Customer customer) {
        return technicalTaskDao.getCustomerTechnicalTask(customer);
    }
}
