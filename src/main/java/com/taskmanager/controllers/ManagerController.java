package com.taskmanager.controllers;

import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;
import com.taskmanager.services.IdentificationService;
import com.taskmanager.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by perestoronin
 */
@Controller
@RequestMapping("/manager/")
public class ManagerController {

    @Autowired
    ManagerService managerService;
    @Autowired
    private IdentificationService identificationService;

    @RequestMapping("/singin")
    public String singinPage() {
        return "/manager/singin";
    }

    @RequestMapping("/singup")
    public String singupPage() {
        return "/manager/singup";
    }

    @RequestMapping("/verification")
    public String verification(@RequestParam ModelMap model, String email, String passwd) {
        Manager manager = new Manager(email, passwd);
        int res = identificationService.singIn(manager);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/manager/singin";
        } else if (res == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/manager/singin";
        }
        return "/manager/myteam";
    }

    @RequestMapping("/registration")
    public String registration(@RequestParam ModelMap model, String name,
                               String email, String passwd, String teamName) {
        Manager manager = new Manager(name, email, passwd);
        Team team = new Team();
        team.setManager(manager);
        team.setName(teamName);
        int res = identificationService.singUp(team);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email already taken");
            return "/manager/singup";
        }
        if (res == -2) {
            model.addAttribute("WarningMessage", "team not exist");
            return "/manager/singup";
        }
        return "/manager/myteam";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        return "/index";
    }

}
