package com.taskmanager.dao.implement;

import com.taskmanager.dao.TeamDao;
import com.taskmanager.model.manager.Manager;
import com.taskmanager.model.team.Team;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by perestoronin
 */
@Repository
public class TeamDaoImpl implements TeamDao {

    private static final Logger logger = Logger.getLogger(TeamDaoImpl.class);
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Team getTeam(Manager manager) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team = (Team) session.createQuery("FROM Team T WHERE T.manager.id=:id")
                .setParameter("id", manager.getId()).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return team;
    }
}
