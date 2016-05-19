package com.taskmanager.controllers;

import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.technicaltask.TechnicalTask;
import com.taskmanager.services.CustomerService;
import com.taskmanager.services.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    public String verification(HttpSession httpSession, ModelMap model, @RequestParam String email, String passwd) {
        Customer customer = new Customer(email, passwd);
        int customerId = identificationService.singIn(customer);
        if (customerId == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/customer/singin";
        } else if (customerId == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/customer/singin";
        }
        customer.setId(customerId);
        httpSession.setAttribute("customer", customer);
        return "/customer/welcomepage";
    }

    @RequestMapping("/registration")
    public String registration(HttpSession httpSession, ModelMap model, @RequestParam String name, String email, String passwd) {
        Customer customer = new Customer(name, email, passwd);
        int customerId = identificationService.singUp(customer);
        if (customerId == -1) {
            model.addAttribute("WarningMessage", "email already taken");
            return "/customer/singup";
        }
        customer.setId(customerId);
        httpSession.setAttribute("customer", customer);
        return "/customer/welcomepage";
    }

    @RequestMapping("/newtask")
    public String newTask() {
        return "/customer/newtask";
    }

    @RequestMapping("/createtask")
    public String createTask(ModelMap model) {
        TechnicalTask technicalTask = new TechnicalTask();
        int task_id = customerService.createNeTechnicalTask(technicalTask);
        model.addAttribute("createmassage", task_id);
        return "/customer/createstatus";
    }

    @RequestMapping("/myscoring")
    public String myScoring(HttpSession httpSession, ModelMap model) {
        Customer customer = (Customer) httpSession.getAttribute("customer");
        if (customer != null)
            model.addAttribute("Projects", customerService.getCustomerScores(customer));
        return "/customer/myscoring";
    }

    @RequestMapping("/mytask")
    public String myTask(HttpSession httpSession, ModelMap model) {
        Customer customer = (Customer) httpSession.getAttribute("customer");
        if (customer != null)
            model.addAttribute("Tasks", customerService.getCustomerTecnicalTask(customer));
        return "/customer/mytask";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        model.remove("customer");
        return "/index";
    }
}
