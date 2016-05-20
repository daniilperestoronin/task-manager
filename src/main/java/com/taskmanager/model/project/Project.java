package com.taskmanager.model.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;
    @Column(name = "p_technical_task_id")
    private Integer technTaskId;
    @Column(name = "developers_team_id")
    private Integer teamId;
    @Column(name = "project_name")
    private String name;
    @Column(name = "project_description")
    private String projectDescription;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<ProjectJob> projectJobList;
    @OneToOne
    @JoinColumn(name = "project_id")
    private ProjectScore projectScore;

    public Project() {
    }

    public Project(String name, String projectDescription, List<ProjectJob> projectJobList, ProjectScore projectScore) {
        this.name = name;
        this.projectDescription = projectDescription;
        this.projectJobList = projectJobList;
        this.projectScore = projectScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTechnTaskId() {
        return technTaskId;
    }

    public void setTechnTaskId(Integer technTaskId) {
        this.technTaskId = technTaskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<ProjectJob> getProjectJobList() {
        return projectJobList;
    }

    public void setProjectJobList(List<ProjectJob> projectJobList) {
        this.projectJobList = projectJobList;
    }

    public ProjectScore getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(ProjectScore projectScore) {
        this.projectScore = projectScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(projectDescription, project.projectDescription) &&
                Objects.equals(projectJobList, project.projectJobList) &&
                Objects.equals(projectScore, project.projectScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projectDescription, projectJobList, projectScore);
    }
}
