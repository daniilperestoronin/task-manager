package com.taskmanager.services;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.ProjectJob;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface DeveloperService {

    void setProjectJobScore(ProjectJob projectJob);

    List<ProjectJob> getDeveloperUndoneProjectJob(Developer developer);

    List<ProjectJob> getDeveloperDoneProjectJob(Developer developer);
}
