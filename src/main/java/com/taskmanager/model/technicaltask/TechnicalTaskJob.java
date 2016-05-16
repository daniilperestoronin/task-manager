package com.taskmanager.model.technicaltask;

import com.taskmanager.model.developer.DeveloperLevel;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by perestoronin
 */

@Entity
@Table(name = "technical_task_job")
public class TechnicalTaskJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer id;
    @Column(name = "job_name", nullable = false)
    private String name;
    @Column(name = "job_description", nullable = false)
    private String description;
    @Column(name = "developer_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    public TechnicalTaskJob() {
    }

    public TechnicalTaskJob(String name, String description, DeveloperLevel developerLevel) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalTaskJob that = (TechnicalTaskJob) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                developerLevel == that.developerLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, developerLevel);
    }
}
