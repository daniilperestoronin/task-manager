package com.taskmanager.dao.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.model.customer.Customer;
import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


/**
 * Created by perestoronin
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int singIn(Customer customer) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * customer_singin(:c_email,:c_password)")
                .setParameter("c_email", customer.getEmail())
                .setParameter("c_password", customer.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public int singUp(Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT * customer_singup(:c_name,:c_email,:c_password)")
                .setParameter("c_name", customer.getName())
                .setParameter("c_email", customer.getEmail())
                .setParameter("c_password", customer.getPasswd())
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
