package com.taskmanager.dao.implement;

import com.taskmanager.dao.TechnicalTaskDao;
import com.taskmanager.model.customer.Customer;
import com.taskmanager.model.technicaltask.TechnicalTask;
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
public class TechnicalTaskDaoImpl implements TechnicalTaskDao {

    private static final Logger logger = Logger.getLogger(TechnicalTaskDaoImpl.class);
    SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }
    @Override
    public int addTechnicalTask(TechnicalTask technicalTask) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(technicalTask);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public List<TechnicalTask> getNotDoneTechnicalTask() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List technicalTasksList = session.createQuery("FROM TechnicalTask").list();
        session.getTransaction().commit();
        session.close();
        return technicalTasksList;
    }

    @Override
    public List<TechnicalTask> getCustomerTechnicalTask(Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List technicalTasksList = session.createQuery("FROM TechnicalTask T WHERE T.customer.id=:id")
                .setParameter("id", customer.getId()).list();
        session.getTransaction().commit();
        session.close();
        return technicalTasksList;
    }

    @Override
    public void updateTechnicalTask(TechnicalTask technicalTask) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(technicalTask);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteTechnicalTask(TechnicalTask technicalTask) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(technicalTask);
        session.getTransaction().commit();
        session.close();
    }
}
