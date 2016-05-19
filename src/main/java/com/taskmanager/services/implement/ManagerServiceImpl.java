package com.taskmanager.services.implement;

import com.taskmanager.dao.ProjectDao;
import com.taskmanager.dao.TeamDao;
import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.team.Team;
import com.taskmanager.model.technicaltask.TechnicalTask;
import com.taskmanager.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by perestoronin
 */
@Service
public class ManagerServiceImpl implements ManagerService {

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
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Developer> getNotBusyDeveloperList(Manager manager) {
        Team team = teamDao.getTeam(manager);
        List<Developer> developers = team.getDeveloperList();
        return developers;
    }

    @Override
    public List<TechnicalTask> getAllUnduneTechnicalTasck() {
        return null;
    }
}
