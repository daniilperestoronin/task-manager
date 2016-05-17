package com.taskmanager.services;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.technicaltask.TechnicalTask;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface ManagerService {

    int createProject(Project project);

    List<Developer> getNotBusyDeveloperList(Manager manager);

    List<TechnicalTask> getAllUnduneTechnicalTasck();
}
