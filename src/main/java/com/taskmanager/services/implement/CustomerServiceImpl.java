package com.taskmanager.services.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.technicaltask.TechnicalTask;
import com.taskmanager.services.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TechnicalTaskDao technicalTaskDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public int createNeTechnicalTask(TechnicalTask technicalTask) {
        try {
            return technicalTaskDao.addTechnicalTask(technicalTask);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Project> getCustomerScores(Customer customer) {
        try {
            return projectDao.getCustomerProject(customer);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TechnicalTask> getCustomerTecnicalTask(Customer customer) {
        try {
            return technicalTaskDao.getCustomerTechnicalTask(customer);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
