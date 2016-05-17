package com.taskmanager.dao.implement;

import com.taskmanager.dao.ManagerDao;
import com.taskmanager.model.manager.Manager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by perestoronin
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    private static final Logger logger = Logger.getLogger(ManagerDaoImpl.class);
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int singIn(Manager manager) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * manager_singin(:c_email,:c_password)")
                .setParameter("c_email", manager.getEmail())
                .setParameter("c_password", manager.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public int singUp(Manager manager) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * manager_singup(:c_name,:c_email,:c_password)")
                .setParameter("c_name", manager.getName())
                .setParameter("c_email", manager.getEmail())
                .setParameter("c_password", manager.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
