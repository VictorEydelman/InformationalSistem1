package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Location findByXAndYAndZ(Long x, Long y, int z){
        Session session = sessionFactory.openSession();
        String hql = "FROM Location l WHERE l.x = :x and l.y = :y and l.z = :z";
        Query<Location> queue=session.createQuery(hql, Location.class);
        queue.setParameter("x", x);
        queue.setParameter("y", y);
        queue.setParameter("z", z);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }

    public Location findById(int id){
        Session session = sessionFactory.openSession();
        String hql = "FROM Location l WHERE l.id = :id";
        Query<Location> queue=session.createQuery(hql, Location.class);
        queue.setParameter("id", id);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }
}