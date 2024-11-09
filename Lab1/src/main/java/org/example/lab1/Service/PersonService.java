package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.enums.Color;
import org.example.lab1.entities.enums.Country;
import org.example.lab1.entities.Location;
import org.example.lab1.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
    public Person find(String name, Color eyeColor, Color hairColor, Location location,
                       double weight, Country nationality){
        Session session = sessionFactory.openSession();
        String hql = "FROM Person l WHERE l.name = :name and l.eyeColor = :eyeColor " +
                "and l.hairColor = :hairColor and l.location = :location and l.weight = :weight" +
                " and l.nationality = :nationality";
        Query<Person> queue=session.createQuery(hql, Person.class);
        queue.setParameter("name", name);
        queue.setParameter("eyeColor", eyeColor);
        queue.setParameter("hairColor", hairColor);
        queue.setParameter("location", location);
        queue.setParameter("weight", weight);
        queue.setParameter("nationality", nationality);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }

    public Person findById(int id){
        Session session = sessionFactory.openSession();
        String hql = "FROM Person l WHERE l.id = :id";
        Query<Person> queue=session.createQuery(hql, Person.class);
        queue.setParameter("id", id);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }
}