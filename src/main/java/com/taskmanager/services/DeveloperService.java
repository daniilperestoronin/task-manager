package com.taskmanager.services;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.ProjectJob;

import java.util.List;

/**
 * Created by perestoronin
 */
public interface DeveloperService {

    String setProjectJobScore(ProjectJob projectJob);

    List<ProjectJob> getDeveloperUnsetProjectJob(Developer developer);

    List<ProjectJob> getDeveloperSetProjectJob(Developer developer);
}
