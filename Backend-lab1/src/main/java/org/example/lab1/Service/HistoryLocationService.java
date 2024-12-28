package org.IS.lab1.Service;

import lombok.AllArgsConstructor;
import org.IS.lab1.entities.HistoryLocation;
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
}