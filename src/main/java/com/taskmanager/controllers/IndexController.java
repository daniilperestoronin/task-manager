package com.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by perestoronin
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String welcomePage(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
    }

}