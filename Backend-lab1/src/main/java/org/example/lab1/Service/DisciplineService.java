package org.example.lab1.Service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public void add(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(discipline);
        tx.commit();
    }
    public void update(Discipline discipline){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
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
        if(!queue.list().isEmpty()){
            return queue.list().get(0);
        } else{
            return null;
        }
    }
    public void saves(Discipline[] disciplines) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            for (Discipline discipline:disciplines) {
                session.persist(discipline);
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
