package com.taskmanager.model.customer;

import com.taskmanager.model.identification.Identification;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "customer")
public class Customer implements Identification, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;
    @Column(name = "customer_name", nullable = false)
    private String name;
    @Column(name = "customer_email", nullable = false)
    private String email;
    @Column(name = "customer_password", nullable = false)
    private String passwd;

    public Customer() {
    }

    public Customer(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public Customer(String name, String email, String passwd) {
        this.name = name;
        this.passwd = passwd;
        this.email = email;
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
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(passwd, customer.passwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, passwd);
    }
}
