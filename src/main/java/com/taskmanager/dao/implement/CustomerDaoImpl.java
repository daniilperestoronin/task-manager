package com.taskmanager.dao.implement;

import com.taskmanager.dao.CustomerDao;
import com.taskmanager.model.customer.Customer;
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
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);
    private SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    @Override
    public int singIn(Customer customer) throws Exception {
        try {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT customer_singin(:c_email,:c_password)")
                .setParameter("c_email", customer.getEmail())
                .setParameter("c_password", customer.getPasswd())
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
    public int singUp(Customer customer) throws Exception {
        try {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer result = (Integer) session.createSQLQuery("SELECT customer_singup(:c_name,:c_email,:c_password)")
                .setParameter("c_name", customer.getName())
                .setParameter("c_email", customer.getEmail())
                .setParameter("c_password", customer.getPasswd())
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
