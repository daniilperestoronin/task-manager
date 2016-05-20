package com.taskmanager.controllers;

import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectScore;
import com.taskmanager.model.team.Team;
import com.taskmanager.services.IdentificationService;
import com.taskmanager.services.ManagerService;
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
    public String verification(HttpSession httpSession, ModelMap model, @RequestParam String email, String passwd) {
        Manager manager = new Manager(email, passwd);
        int res = identificationService.singIn(manager);
        if (res == -1) {
            model.addAttribute("WarningMessage", "email is not registered");
            return "/manager/singin";
        } else if (res == -2) {
            model.addAttribute("WarningMessage", "incorrect password");
            return "/manager/singin";
        }
        manager.setId(res);
        httpSession.setAttribute("manager", manager);
        return "/manager/welcomepage";
    }

    @RequestMapping("/registration")
    public String registration(HttpSession httpSession, ModelMap model, @RequestParam String name,
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
        manager.setId(res);
        httpSession.setAttribute("manager", manager);
        return "/manager/welcomepage";
    }

    @RequestMapping("/technicaltasks")
    public String listUndoneTechnicalTask(ModelMap model) {
        model.addAttribute("technicalTasks", managerService.getAllUndoneTechnicalTasck());
        return "/manager/technicaltasks";
    }

    @RequestMapping("/projects")
    public String listDeveloperProject(HttpSession httpSession, ModelMap model) {
        Manager manager = (Manager) httpSession.getAttribute("manager");
        model.addAttribute("projects", managerService.getTeamProject(manager));
        return "/manager/projects";
    }

    @RequestMapping("/team")
    public String teamList(HttpSession httpSession, ModelMap model) {
        Manager manager = (Manager) httpSession.getAttribute("manager");
        model.addAttribute("team", managerService.getTeam(manager));
        return "/manager/team";
    }

    @RequestMapping("/newproject")
    public String newProject(ModelMap model, @RequestParam String id) {
        model.addAttribute("technicalId", id);
        return "/manager/newproject";
    }

    @RequestMapping("/createproject")
    public String newProject(HttpSession httpSession, ModelMap model, @RequestParam String taskId,
                             String name, String description, String score) {
        ProjectScore projectScore = new ProjectScore();
        projectScore.setScore(Double.parseDouble(score));
        Project project = new Project();
        project.setTechnTaskId(Integer.parseInt(taskId));
        project.setName(name);
        project.setProjectDescription(description);
        project.setProjectScore(projectScore);
        if (managerService.createProject(project) != -1) {
            model.addAttribute("createStatus", "Project successfully created");
        } else {
            model.addAttribute("createStatus", "The project is not created, an error occurred");
        }
        return "/manager/createstatus";
    }

    @RequestMapping("/singout")
    public String singout(ModelMap model) {
        return "/index";
    }

}
