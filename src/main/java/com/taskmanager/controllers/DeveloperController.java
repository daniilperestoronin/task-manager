package com.taskmanager.controllers;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.developer.DeveloperLevel;
import com.taskmanager.services.DeveloperService;
import com.taskmanager.services.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by perestoronin
 */
@Controller
@RequestMapping("/developer/")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;
    @Autowired
    private IdentificationService identificationService;

    @RequestMapping("/singin")
    public String singinPage() {
        return "/developer/singin";
    }

    @RequestMapping("/singup")
    public String singupPage() {
        return "/developer/singup";
    }

    @RequestMapping("/verification")
    public String verification(ModelMap model, @RequestParam String email, String passwd) {
        Developer developer = new Developer(email, passwd);
        int res = identificationService.singIn(developer);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/developer/singin";
        } else if (res == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/developer/singin";
        }
        return "/developer/myjobs";
    }

    @RequestMapping("/registration")
    public String registration(ModelMap model, @RequestParam String name,
                               String email, String passwd, String level, String teamId) {
        Developer developer = new Developer(Integer.parseInt(teamId), name, email, passwd, DeveloperLevel.stringParser(level), true);
        int res = identificationService.singUp(developer);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email already taken");
            return "/developer/singup";
        }
        if (res == -2) {
            model.addAttribute("WarningMessage", "team not exist");
            return "/developer/singup";
        }
        return "/developer/myjobs";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        return "/index";
    }
}
