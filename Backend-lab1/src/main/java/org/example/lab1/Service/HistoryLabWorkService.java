package org.IS.lab1.Service;

import lombok.AllArgsConstructor;
import org.IS.lab1.entities.HistoryLabWorks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryLabWorkService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryLabWorks historyLabWork){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(historyLabWork);
        tx.commit();
    }
}
