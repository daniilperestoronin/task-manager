package com.taskmanager.dao;

import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;

/**
 * Created by perestoronin
 */
public interface ManagerDao {

    int singIn(Manager manager);

    int singUp(Team team);
}
