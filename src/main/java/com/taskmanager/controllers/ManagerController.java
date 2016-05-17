package com.taskmanager.controllers;

import com.taskmanager.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by perestoronin
 */
@Controller
@RequestMapping("/manager/")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/singin")
    public String singinPage(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/manager/singin";
    }

    @RequestMapping("/singup")
    public String singupPage(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/manager/singup";
    }

}
