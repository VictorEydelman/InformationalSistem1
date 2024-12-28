package org.example.lab1.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.lab1.entities.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoordinateSevice {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void add(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(coordinates);
        tx.commit();
    }
    public void update(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(coordinates);
        tx.commit();
    }
    public void delete(Coordinates coordinates){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(coordinates);
        tx.commit();
    }

    public Coordinates findByXAndY(float x, Integer y){
        Session session = sessionFactory.openSession();
        String hql = "FROM Coordinates l WHERE l.x = :x and l.y = :y";
        Query<Coordinates> queue=session.createQuery(hql, Coordinates.class);
        queue.setParameter("x", x);
        queue.setParameter("y", y);
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }

    public Coordinates findById(int id){
        Session session = sessionFactory.openSession();
        String hql = "FROM Coordinates l WHERE l.id = :id";
        Query<Coordinates> queue=session.createQuery(hql, Coordinates.class);
        queue.setParameter("id", id);
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }
    public void saves(Coordinates[] coordinates) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            for (Coordinates coordinate: coordinates) {
                session.persist(coordinate);
            }

            transaction.commit();
            System.out.println("Items saved successfully!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Transaction rolled back.");
            }
            e.printStackTrace();
        }
    }
}
