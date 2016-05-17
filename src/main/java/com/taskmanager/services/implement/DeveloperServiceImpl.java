package com.taskmanager.services.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TechnicalTaskDao technicalTaskDao;
    @Autowired
    private ProjectDao projectDao;


    @Override
    public void setProjectJobScore(ProjectJob projectJob) {

    }

    @Override
    public List<ProjectJob> getDeveloperUndoneProjectJob(Developer developer) {
        return null;
    }

    @Override
    public List<ProjectJob> getDeveloperDoneProjectJob(Developer developer) {
        return null;
    }
}
