package com.taskmanager.services;

import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.team.Team;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface ManagerService {

    int createProject(Project project);

    Team getTeam(Manager manager);

    List<TechnicalTask> getAllUndoneTechnicalTasck();

    List<Project> getTeamProject(Manager manager);
}
