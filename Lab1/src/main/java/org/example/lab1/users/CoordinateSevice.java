package org.example.lab1.users;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.Coordinates;
import org.example.lab1.entities.LabWork;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoordinateSevice {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        //System.out.print(session);
        session.persist(coordinates);
        tx.commit();
    }
    public void update(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(coordinates);
        session.update(coordinates);
        tx.commit();
    }
    public void delete(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(coordinates);
        tx.commit();
    }
}
