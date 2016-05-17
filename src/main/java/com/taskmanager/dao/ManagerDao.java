package com.taskmanager.dao;

import com.taskmanager.model.manager.Manager;

/**
 * Created by perestoronin
 */
public interface ManagerDao {

    int singIn(Manager manager);

    int singUp(Manager manager);
}
