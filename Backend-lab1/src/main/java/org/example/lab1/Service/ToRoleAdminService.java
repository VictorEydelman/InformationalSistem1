package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.ToRoleAdmin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToRoleAdminService {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void add(ToRoleAdmin toRoleAdmin){

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(toRoleAdmin);
        tx.commit();
    }
    public void delete(ToRoleAdmin toRoleAdmin){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(toRoleAdmin);
        tx.commit();
    }
    public ToRoleAdmin findByUsername(String username){
        Session session = sessionFactory.openSession();
        String hql = "FROM ToRoleAdmin u WHERE u.username = :username";
        Query<ToRoleAdmin> queue=session.createQuery(hql, ToRoleAdmin.class);
        var q =queue.setParameter("username", username);
        if(!q.list().isEmpty()){
            return q.list().get(0);
        } else{
            return null;
        }
    }
    public List<ToRoleAdmin> findAll(){
        Session session = sessionFactory.openSession();
        Query<ToRoleAdmin> queue=session.createQuery("from ToRoleAdmin", ToRoleAdmin.class);
        return queue.list();
    }
}
