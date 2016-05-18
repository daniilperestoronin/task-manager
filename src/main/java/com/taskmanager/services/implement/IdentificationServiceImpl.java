package com.taskmanager.services.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.dao.DeveloperDao;
import com.taskmanager.dao.ManagerDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.identification.Identification;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;
import com.taskmanager.services.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by perestoronin
 */
@Service
public class IdentificationServiceImpl implements IdentificationService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DeveloperDao developerDao;
    @Autowired
    private ManagerDao managerDao;

    @Override
    public int singIn(Identification identificationObject) {
        if (identificationObject instanceof Customer)
            return customerDao.singIn((Customer) identificationObject);
        if (identificationObject instanceof Developer)
            return developerDao.singIn((Developer) identificationObject);
        if (identificationObject instanceof Manager)
            return managerDao.singIn((Manager) identificationObject);
        return 0;
    }

    @Override
    public int singUp(Identification identificationObject) {
        if (identificationObject instanceof Customer)
            return customerDao.singUp((Customer) identificationObject);
        if (identificationObject instanceof Developer)
            return developerDao.singUp((Developer) identificationObject);
        if (identificationObject instanceof Team)
            return managerDao.singUp((Team) identificationObject);
        return 0;
    }
}
