package com.taskmanager.dao.implement;

import com.taskmanager.dao.DeveloperDao;
import com.taskmanager.model.developer.Developer;
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
public class DeveloperDaoImpl implements DeveloperDao {

    private static final Logger logger = Logger.getLogger(DeveloperDaoImpl.class);
    SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    @Override
    public int singIn(Developer developer) throws Exception {
        try {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT developer_singin(:d_email,:d_password)")
                .setParameter("d_email", developer.getEmail())
                .setParameter("d_password", developer.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DB error" + e.getMessage());
        }
    }

    @Override
    public int singUp(Developer developer) throws Exception {
        try {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery(
                "SELECT developer_singup(:d_name,:d_email,:d_password,:d_level,:id_team)")
                .setParameter("d_name", developer.getName())
                .setParameter("d_email", developer.getEmail())
                .setParameter("d_password", developer.getPasswd())
                .setParameter("d_level", developer.getDeveloperLevel().toString())
                .setParameter("id_team", developer.getTeamId())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("DB error" + e.getMessage());
        }
    }
}
