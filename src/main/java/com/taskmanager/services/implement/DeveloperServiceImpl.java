package com.taskmanager.services.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.services.DeveloperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private static final Logger logger = Logger.getLogger(DeveloperServiceImpl.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TechnicalTaskDao technicalTaskDao;
    @Autowired
    private ProjectDao projectDao;


    @Override
    public String setProjectJobScore(ProjectJob projectJob) {
        try {
            projectDao.setProjectJobTime(projectJob);
            return "Score successfully set";
        } catch (Exception e) {
            return "Score set error";
        }
    }

    @Override
    public List<ProjectJob> getDeveloperUnsetProjectJob(Developer developer) {
        try {
            return projectDao.getDeveloperUnsetProjectList(developer);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProjectJob> getDeveloperSetProjectJob(Developer developer) {
        try {
            return projectDao.getDeveloperSetProjectList(developer);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
