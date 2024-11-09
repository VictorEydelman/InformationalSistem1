package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.Discipline;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisciplineService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
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

    public Discipline findByNameAndLectureHours(String name, Long LectureHours){
        Session session = sessionFactory.openSession();
        String hql = "FROM Discipline l WHERE l.name = :name and l.lectureHours = :lectureHours";
        Query<Discipline> queue=session.createQuery(hql, Discipline.class);
        queue.setParameter("name", name);
        queue.setParameter("lectureHours", LectureHours);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }

    public Discipline findById(int id){
        Session session = sessionFactory.openSession();
        String hql = "FROM Discipline l WHERE l.id = :id";
        Query<Discipline> queue=session.createQuery(hql, Discipline.class);
        queue.setParameter("id", id);
        System.out.println(queue.list().size());
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }
}
