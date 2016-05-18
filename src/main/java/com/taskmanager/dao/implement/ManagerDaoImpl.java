package com.taskmanager.dao.implement;

import com.taskmanager.dao.ManagerDao;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Created by perestoronin
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    private static final Logger logger = Logger.getLogger(ManagerDaoImpl.class);
    SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    @Override
    public int singIn(Manager manager) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT manager_singin(:m_email,:m_password)")
                .setParameter("m_email", manager.getEmail())
                .setParameter("m_password", manager.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public int singUp(Team team) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT manager_singup(:m_name,:m_email,:m_password,:team_name)")
                .setParameter("m_name", team.getManager().getName())
                .setParameter("m_email", team.getManager().getEmail())
                .setParameter("m_password", team.getManager().getPasswd())
                .setParameter("team_name", team.getName())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
