package com.taskmanager.services.implement;

import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TeamDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.team.Team;
import com.taskmanager.model.technicaltask.TechnicalTask;
import com.taskmanager.services.ManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private static final Logger logger = Logger.getLogger(ManagerServiceImpl.class);
    @Autowired
    private TechnicalTaskDao technicalTaskDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private TeamDao teamDao;


    @Override
    public int createProject(Project project) {
        try {
            return projectDao.addProject(project);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Team getTeam(Manager manager) {
        return teamDao.getTeam(manager);
    }

    @Override
    public List<TechnicalTask> getAllUndoneTechnicalTasck() {
        try {
            return technicalTaskDao.getNotDoneTechnicalTask();
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
