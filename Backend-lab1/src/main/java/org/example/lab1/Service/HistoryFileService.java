package org.example.lab1.Service;

import org.example.lab1.entities.HistoryFile;
import org.example.lab1.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryFileService {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryFile historyFile){
        System.out.println(historyFile);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(historyFile);
        System.out.println(2);
        tx.commit();
    }
    public List<HistoryFile> getAll(){
        Session session = sessionFactory.openSession();
        Query<HistoryFile> query = session.createQuery("FROM HistoryFile l",HistoryFile.class);
        return query.list();
    }
    public List<HistoryFile> getbyuser(User user){
        Session session = sessionFactory.openSession();
        Query<HistoryFile> query = session.createQuery("FROM HistoryFile l WHERE l.username=:username",HistoryFile.class);
        query.setParameter("username", user.getUsername());
        return query.list();
    }
}
