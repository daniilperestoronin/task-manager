package com.taskmanager.dao;

import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;

/**
 * Created by perestoronin
 */
public interface TeamDao {

    Team getTeam(Manager manager);
}
