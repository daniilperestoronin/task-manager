package com.taskmanager.controllers;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.services.CustomerService;
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
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private IdentificationService identificationService;

    @RequestMapping("/singin")
    public String singinPage() {
        return "/customer/singin";
    }

    @RequestMapping("/singup")
    public String singupPage() {
        return "/customer/singup";
    }

    @RequestMapping("/verification")
    public String verification(ModelMap model, @RequestParam() String email, String passwd) {
        Customer customer = new Customer("test", "test", "test");
        int res = identificationService.singIn(customer);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/customer/singin";
        } else if (res == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/customer/singin";
        }
        return "/customer/mytask";
    }

    @RequestMapping("/registration")
    public String registration(ModelMap model, @RequestParam() String name, String email, String passwd) {
        Customer customer = new Customer("test", "test", "test");
        int res = identificationService.singUp(customer);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email already taken");
            return "/customer/singup";
        }
        return "/customer/mytask";
    }

    @RequestMapping("/myscoring")
    public String myScoring(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/customer/myscoring";
    }

    @RequestMapping("/newtask")
    public String newTask(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/customer/newtask";
    }

    @RequestMapping("/mytask")
    public String myTask(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/customer/mytask";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/index";
    }
}
