package org.example.lab1.users;

import lombok.AllArgsConstructor;
import org.example.lab1.DB.LabWorkResponce;
import org.example.lab1.entities.Coordinates;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Location location){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        //System.out.print(session);
        session.persist(location);
        tx.commit();
    }
    public void update(Location location){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(location);
        session.update(location);
        tx.commit();
    }

    public void delete(Location location){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(location);
        tx.commit();
    }
}
