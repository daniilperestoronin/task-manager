package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.model.team.Team;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface ProjectDao {

    int addProject(Project project);

    List<Project> getTeamProject(Team team);

    List<Project> getCustomerProject(Customer customer);

    void updateProjectJob(ProjectJob projectJob);
}
