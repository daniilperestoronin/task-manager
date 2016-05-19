package com.taskmanager.controllers;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.developer.DeveloperLevel;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.services.DeveloperService;
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
    public String verification(HttpSession httpSession, ModelMap model, @RequestParam String email, String passwd) {
        Developer developer = new Developer(email, passwd);
        int developerID = identificationService.singIn(developer);
        if (developerID == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/developer/singin";
        } else if (developerID == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/developer/singin";
        }
        developer.setId(developerID);
        httpSession.setAttribute("developer", developer);
        return "developer/welcomepage";
    }

    @RequestMapping("/registration")
    public String registration(HttpSession httpSession, ModelMap model, @RequestParam String name,
                               String email, String passwd, String level, String teamId) {
        Developer developer = new Developer(Integer.parseInt(teamId), name, email, passwd, DeveloperLevel.stringParser(level), true);
        int developerId = identificationService.singUp(developer);
        if (developerId == -1) {
            model.addAttribute("WarningMessage", "email already taken");
            return "/developer/singup";
        }
        if (developerId == -2) {
            model.addAttribute("WarningMessage", "team not exist");
            return "/developer/singup";
        }
        developer.setId(developerId);
        httpSession.setAttribute("developer", developer);
        return "/developer/welcomepage";
    }

    @RequestMapping("/tasksin")
    public String getDeveloperJobsInWork(HttpSession httpSession, ModelMap model) {
        Developer developer = (Developer) httpSession.getAttribute("developer");
        model.addAttribute("tasksIn", developerService.getDeveloperSetProjectJob(developer));
        return "/developer/tasksin";
    }

    @RequestMapping("/newtasks")
    public String getDeveloperNewJobs(HttpSession httpSession, ModelMap model) {
        Developer developer = (Developer) httpSession.getAttribute("developer");
        model.addAttribute("tasksIn", developerService.getDeveloperUnsetProjectJob(developer));
        return "/developer/newtasks";
    }

    @RequestMapping("/settime")
    public String setJobScore(HttpSession httpSession, ModelMap model, @RequestParam String developerTime) {
        ProjectJob projectJob = new ProjectJob();
        Developer developer = (Developer) httpSession.getAttribute("developer");
        projectJob.setId(developer.getId());
        projectJob.setDeveloperTime(developerTime);
        model.addAttribute("setStatus", developerService.setProjectJobScore(projectJob));
        return "developer/settime";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        return "/index";
    }
}
