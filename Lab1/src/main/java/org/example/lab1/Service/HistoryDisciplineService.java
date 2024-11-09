package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.HistoryDiscipline;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryDisciplineService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryDiscipline historyDiscipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(historyDiscipline);
        tx.commit();
    }
    public void update(HistoryDiscipline historyDiscipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(historyDiscipline);
        session.update(historyDiscipline);
        tx.commit();
    }

    public void delete(HistoryDiscipline historyDiscipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(historyDiscipline);
        tx.commit();
    }
}
