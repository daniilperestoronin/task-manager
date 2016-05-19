package com.taskmanager.dao;

import com.taskmanager.model.developer.Developer;

/**
 * Created by perestoronin
 */
public interface DeveloperDao {

    int singIn(Developer developer) throws Exception;

    int singUp(Developer developer) throws Exception;
}
