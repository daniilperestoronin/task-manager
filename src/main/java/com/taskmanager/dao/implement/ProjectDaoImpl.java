package com.taskmanager.dao.implement;

import com.taskmanager.dao.ProjectDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.developer.Developer;
import com.taskmanager.model.project.Project;
import com.taskmanager.model.project.ProjectJob;
import com.taskmanager.model.team.Team;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by perestoronin
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
    SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    @Override
    public int addProject(Project project) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Integer projectID = (Integer) session.save(project);
            session.getTransaction().commit();
            session.close();
            return projectID;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DAO Error:" + e.getMessage());
        }
    }

    @Override
    public List<Project> getTeamProject(Team team) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List technicalTasksList = session.createSQLQuery("SELECT *\n" +
                    "FROM project WHERE project.developers_team_id =:id")
                    .setParameter("id", team.getId()).list();
            session.getTransaction().commit();
            session.close();
            return technicalTasksList;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DAO Error:" + e.getMessage());
        }
    }

    @Override
    public List<Project> getCustomerProject(Customer customer) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List technicalTasksList = session.createQuery("SELECT p FROM Project p, TechnicalTask t " +
                    "WHERE  p.technTaskId=t.id AND t.customer.id=:id AND p.projectScore.score is not null")
                    .setParameter("id", customer.getId()).list();
            session.getTransaction().commit();
            session.close();
            return technicalTasksList;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DAO Error:" + e.getMessage());
        }
    }

    @Override
    public List<ProjectJob> getDeveloperUnsetProjectList(Developer developer) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List developerUnsrtProjectList = session.createQuery("FROM ProjectJob p " +
                    "WHERE p.developerId=:id AND p.developerTime IS NULL")
                    .setParameter("id", 1).list();
            session.getTransaction().commit();
            session.close();
            return developerUnsrtProjectList;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DAO Error:" + e.getMessage());
        }
    }

    @Override
    public List<ProjectJob> getDeveloperSetProjectList(Developer developer) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List developerSetProjectList = session.createQuery("FROM ProjectJob p " +
                    "WHERE p.developerId=:id AND p.developerTime IS NOT NULL")
                    .setParameter("id", 1).list();
            session.getTransaction().commit();
            session.close();
            return developerSetProjectList;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DAO Error:" + e.getMessage());
        }
    }

    @Override
    public void setProjectJobTime(ProjectJob projectJob) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createQuery("UPDATE ProjectJob " +
                    "SET developerTime=:devtime WHERE developerId =:id")
                    .setParameter("devtime", projectJob.getDeveloperTime())
                    .setParameter("id", projectJob.getId());
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("database error:" + e.getMessage());
        }
    }
}
