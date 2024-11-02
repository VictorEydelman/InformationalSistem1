package org.example.lab1.users;

import jakarta.persistence.NoResultException;
import org.example.lab1.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            //user.setId(1L);
            System.out.println(user);
            //System.out.print(session);

            session.persist(user);
            System.out.println(user);
            tx.commit();
        }
    }
    public User getByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Query<User> queue = session.createQuery("FROM User WHERE username = :username and password = :password",User.class);
        System.out.println(queue);
        queue.setParameter("username", username);
        queue.setParameter("password", password);
        try {
            return queue.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    public User getByUsername(String username) {
        Session session = sessionFactory.openSession();
        System.out.println(username);
        Query<User> queue = session.createQuery("FROM User WHERE username = :username",User.class);

        queue.setParameter("username", username);
        System.out.println(queue);
        return queue.uniqueResult();
    }
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
    public void update(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }
    }
    public Long sizeUsers() {
        try(Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM User";
            return (Long) session.createQuery(hql).uniqueResult();
        }
    }
}
