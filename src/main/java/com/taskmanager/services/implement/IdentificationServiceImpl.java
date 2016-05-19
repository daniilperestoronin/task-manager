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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by perestoronin
 */
@Service
public class IdentificationServiceImpl implements IdentificationService {

    private static final Logger logger = Logger.getLogger(IdentificationServiceImpl.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DeveloperDao developerDao;
    @Autowired
    private ManagerDao managerDao;

    @Override
    public int singIn(Identification identificationObject) {
        try {
            if (identificationObject instanceof Customer)
                return customerDao.singIn((Customer) identificationObject);
            if (identificationObject instanceof Developer)
                return developerDao.singIn((Developer) identificationObject);
            if (identificationObject instanceof Manager)
                return managerDao.singIn((Manager) identificationObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return 0;
    }

    @Override
    public int singUp(Identification identificationObject) {
        try {
            if (identificationObject instanceof Customer)
                return customerDao.singUp((Customer) identificationObject);
            if (identificationObject instanceof Developer)
                return developerDao.singUp((Developer) identificationObject);
            if (identificationObject instanceof Team)
                return managerDao.singUp((Team) identificationObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return 0;
    }
}
