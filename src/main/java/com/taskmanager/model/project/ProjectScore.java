package com.taskmanager.model.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "project_score")
public class ProjectScore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_score_id")
    private int id;
    @Column(name = "project_score")
    private double score;

    public ProjectScore() {
    }

    public ProjectScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectScore that = (ProjectScore) o;
        return id == that.id &&
                Double.compare(that.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }
}
