package com.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by perestoronin
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String welcomePage() {
        return "index";
    }
}