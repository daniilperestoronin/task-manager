package com.taskmanager.dao.implement;

import com.taskmanager.dao.ProjectDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.model.team.Team;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by perestoronin
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addProject(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer projectID = (Integer) session.save(project);
        session.getTransaction().commit();
        session.close();
        return projectID;
    }

    @Override
    public List<Project> getTeamProject(Team team) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List technicalTasksList = session.createQuery("FROM Project P WHERE P.customer.id=:id")
                .setParameter("id", team.getId()).list();
        session.getTransaction().commit();
        session.close();
        return technicalTasksList;
    }

    @Override
    public List<Project> getCustomerProject(Customer customer) {
        return null;
    }

    @Override
    public void updateProjectJob(ProjectJob projectJob) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(projectJob);
        session.getTransaction().commit();
        session.close();
    }
}
