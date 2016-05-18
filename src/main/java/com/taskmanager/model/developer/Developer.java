package com.taskmanager.model.developer;

import com.taskmanager.model.identification.Identification;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "developer")
public class Developer implements Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private int id;
    @Column(name = "developers_team_id")
    private int teamId;
    @Column(name = "developer_name", nullable = false)
    private String name;
    @Column(name = "developer_email", nullable = false)
    private String email;
    @Column(name = "developer_pass", nullable = false)
    private String passwd;
    @Column(name = "developer_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;
    @Column(name = "is_busy", nullable = false)
    private boolean isBusy;


    public Developer() {
    }

    public Developer(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public Developer(int teamId, String name, String email, String passwd, DeveloperLevel developerLevel, boolean isBusy) {
        this.teamId = teamId;
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.developerLevel = developerLevel;
        this.isBusy = isBusy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id &&
                isBusy == developer.isBusy &&
                Objects.equals(name, developer.name) &&
                Objects.equals(email, developer.email) &&
                Objects.equals(passwd, developer.passwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, passwd, isBusy);
    }
}
