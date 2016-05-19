package com.taskmanager.dao;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.model.team.Team;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface ProjectDao {

    int addProject(Project project) throws Exception;

    List<Project> getTeamProject(Team team) throws Exception;

    List<Project> getCustomerProject(Customer customer) throws Exception;

    List<ProjectJob> getDeveloperUnsetProjectList(Developer developer) throws Exception;

    List<ProjectJob> getDeveloperSetProjectList(Developer developer) throws Exception;

    void setProjectJobTime(ProjectJob projectJob) throws Exception;
}
