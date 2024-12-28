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
        session.persist(historyPerson);
        tx.commit();
    }
}