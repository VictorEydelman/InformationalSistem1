package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.HistoryCoordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryCoordinateSevice {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryCoordinates historyCoordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        //System.out.print(session);
        session.merge(historyCoordinates);
        tx.commit();
    }
    public void update(HistoryCoordinates historyCoordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(historyCoordinates);
        session.update(historyCoordinates);
        tx.commit();
    }
    public void delete(HistoryCoordinates historyCoordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(historyCoordinates);
        tx.commit();
    }
}
