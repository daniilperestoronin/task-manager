package com.taskmanager.model.manager;

import com.taskmanager.model.identification.Identification;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "manager")
public class Manager implements Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int id;
    @Column(name = "manager_name", nullable = false)
    private String name;
    @Column(name = "manager_email ", nullable = false)
    private String email;
    @Column(name = "manager_passwd", nullable = false)
    private String passwd;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return id == manager.id &&
                Objects.equals(name, manager.name) &&
                Objects.equals(email, manager.email) &&
                Objects.equals(passwd, manager.passwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, passwd);
    }
}
