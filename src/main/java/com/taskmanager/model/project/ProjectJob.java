package com.taskmanager.model.project;

import com.taskmanager.model.developer.DeveloperLevel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "project_job")
public class ProjectJob implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_job_id")
    private int id;
    @Column(name = "developer_id")
    private int developerId;
    @Column(name = "project_job_name")
    private String name;
    @Column(name = "project_job_description")
    private String description;
    @Column(name = "developer_level")
    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;
    @Column(name = "developer_time")
    private String developerTime;

    public ProjectJob() {
    }

    public ProjectJob(int developerId, String name, String description, DeveloperLevel developerLevel) {
        this.developerId = developerId;
        this.name = name;
        this.description = description;
        this.developerLevel = developerLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }

    public String getDeveloperTime() {
        return developerTime;
    }

    public void setDeveloperTime(String developerTime) {
        this.developerTime = developerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectJob that = (ProjectJob) o;
        return id == that.id &&
                developerId == that.developerId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                developerLevel == that.developerLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, developerId, name, description, developerLevel);
    }
}
