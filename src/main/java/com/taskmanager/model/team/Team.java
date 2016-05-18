package com.taskmanager.model.team;

import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.identification.Identification;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.project.Project;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by perestoronin
 */

@Entity
@Table(name = "developers_team")
public class Team implements Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developers_team_id")
    private int id;
    @Column(name = "developers_team_name")
    private String name;
    @OneToOne
    @JoinColumn(name = "developers_team_id")
    private Manager manager;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "developers_team_id")
    private List<Developer> developerList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "developers_team_id")
    private List<Project> projectList;

    public Team() {
    }

    public Team(String name, Manager manager, List<Developer> developerList, List<Project> projectList) {
        this.name = name;
        this.manager = manager;
        this.developerList = developerList;
        this.projectList = projectList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(manager, team.manager) &&
                Objects.equals(developerList, team.developerList) &&
                Objects.equals(projectList, team.projectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manager, developerList, projectList);
    }
}
