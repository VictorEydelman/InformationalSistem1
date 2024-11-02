package org.example.lab1.users;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.Coordinates;
import org.example.lab1.entities.Discipline;
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
public class DisciplineService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        //System.out.print(session);
        session.persist(discipline);
        tx.commit();
    }
    public void update(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(discipline);
        session.update(discipline);
        tx.commit();
    }

    public void delete(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(discipline);
        tx.commit();
    }
}
