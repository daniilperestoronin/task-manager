package com.taskmanager.dao.implement;

import com.taskmanager.dao.TeamDao;
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
public class TeamDaoImpl implements TeamDao {

    private static final Logger logger = Logger.getLogger(TeamDaoImpl.class);
    SessionFactory sessionFactory;

    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        this.sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }
    @Override
    public Team getTeam(Manager manager) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Team team = (Team) session.createQuery("FROM Team T WHERE T.manager.id=:id")
                    .setParameter("id", manager.getId()).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return team;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("database error:" + e.getMessage());
        }
    }
}
