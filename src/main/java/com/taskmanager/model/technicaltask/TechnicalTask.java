package com.taskmanager.model.technicaltask;

import com.taskmanager.model.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by perestoronin
 */
@Entity
@Table(name = "technical_task")
public class TechnicalTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technical_task_id")
    private Integer id;
    @Column(name = "task_name", nullable = false)
    private String name;
    @Column(name = "task_description", nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "technical_task_id")
    private List<TechnicalTaskJob> technicalTaskJobList;

    public TechnicalTask() {
    }

    public TechnicalTask(String name, String description, Customer customer, List<TechnicalTaskJob> technicalTaskJobList) {
        this.name = name;
        this.description = description;
        this.customer = customer;
        this.technicalTaskJobList = technicalTaskJobList;
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

    public List<TechnicalTaskJob> getTechnicalTaskJobList() {
        return technicalTaskJobList;
    }

    public void setTechnicalTaskJobList(List<TechnicalTaskJob> technicalTaskJobList) {
        this.technicalTaskJobList = technicalTaskJobList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalTask that = (TechnicalTask) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(technicalTaskJobList, that.technicalTaskJobList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, customer, technicalTaskJobList);
    }
}
