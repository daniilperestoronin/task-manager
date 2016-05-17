package com.taskmanager.dao.implement;

import com.taskmanager.dao.DeveloperDao;
import com.taskmanager.model.developer.Developer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by perestoronin
 */
@Repository
public class DeveloperDaoImpl implements DeveloperDao {

    private static final Logger logger = Logger.getLogger(DeveloperDaoImpl.class);
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int singIn(Developer developer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * developer_singin(:c_email,:c_password)")
                .setParameter("c_email", developer.getEmail())
                .setParameter("c_password", developer.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public int singUp(Developer developer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * developer_singup(:c_name,:c_email,:c_password)")
                .setParameter("c_name", developer.getName())
                .setParameter("c_email", developer.getEmail())
                .setParameter("c_password", developer.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
