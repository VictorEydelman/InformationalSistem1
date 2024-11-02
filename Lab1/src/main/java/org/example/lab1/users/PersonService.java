package org.example.lab1.users;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.Coordinates;
import org.example.lab1.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        System.out.print(session);
        session.persist(person);
        tx.commit();
    }
    public void update(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(person);
        session.update(person);
        tx.commit();
    }
    public void delete(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(person);
        tx.commit();
    }
}
