package com.taskmanager.model.developer;

import java.io.Serializable;

/**
 * Created by perestoronin
 */
public enum DeveloperLevel implements Serializable {
    JUNIOR, MIDDLE, SENIOR, LEAD, NOLEVEL;

    public static DeveloperLevel stringParser(String level) {
        switch (level) {
            case "JUNIOR":
                return JUNIOR;
            case "MIDDLE":
                return MIDDLE;
            case "SENIOR":
                return SENIOR;
            case "LEAD":
                return LEAD;
            default:
                return NOLEVEL;
        }
    }
}
