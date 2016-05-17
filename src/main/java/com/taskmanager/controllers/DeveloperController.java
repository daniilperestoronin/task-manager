package com.taskmanager.controllers;

import com.taskmanager.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by perestoronin
 */
@Controller
@RequestMapping("/developer/")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @RequestMapping("/singin")
    public String singinPage(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/developer/singin";
    }

    @RequestMapping("/singup")
    public String singupPage(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/developer/singup";
    }
}
