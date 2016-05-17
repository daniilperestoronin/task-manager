package com.taskmanager.dao;

import com.taskmanager.model.developer.Developer;

/**
 * Created by perestoronin
 */
public interface DeveloperDao {

    int singIn(Developer developer);

    int singUp(Developer developer);
}
