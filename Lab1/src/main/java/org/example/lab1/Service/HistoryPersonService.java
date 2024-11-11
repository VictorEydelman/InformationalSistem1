package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.HistoryPerson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryPersonService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryPerson historyPerson){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        System.out.print(historyPerson);
        session.persist(historyPerson);
        tx.commit();
    }
    public void update(HistoryPerson historyPerson){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(historyPerson);
        session.update(historyPerson);
        tx.commit();
    }
    public void delete(HistoryPerson historyPerson){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(historyPerson);
        tx.commit();
    }

}