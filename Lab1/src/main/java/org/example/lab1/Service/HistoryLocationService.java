package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.HistoryLocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryLocationService {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryLocation historyLocation){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(historyLocation);
        tx.commit();
    }
    public void update(HistoryLocation historyLocation){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(historyLocation);
        session.update(historyLocation);
        tx.commit();
    }

    public void delete(HistoryLocation historyLocation){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(historyLocation);
        tx.commit();
    }
}