package com.taskmanager.services;

import com.taskmanager.model.identification.Identification;

/**
 * Created by perestoronin
 */
public interface IdentificationService {

    int singIn(Identification identificationObject);

    int singUp(Identification identificationObject);
}
