package org.example.lab1.users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.example.lab1.DB.LabWorkResponce;
import org.example.lab1.DTO.FilterByIdDTO;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.Location;
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
public class LabWorkService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(LabWork labWork){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        System.out.println(labWork);
        //System.out.print(session);
        session.persist(labWork);
        System.out.println(labWork);
        tx.commit();
    }

    public List<LabWorkResponce> getAll(){
        Session session = sessionFactory.openSession();
        System.out.println("asdasdsadasdsadsdasdasd");
        Query<LabWorkResponce> queue = session.createQuery(
                "FROM LabWork l JOIN " +
                "l.coordinates c JOIN l.discipline JOIN l.person p JOIN p.location", LabWorkResponce.class);
        System.out.println(123);
        System.out.println(queue.list());
        return queue.list();
    }

    public void update(LabWork labWork){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(labWork);
        tx.commit();
    }
    public void delete(Long id, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService){

        //user.setId(1L);
        //System.out.println(labWork);
        //System.out.print(session);
        System.out.println(id);
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        LabWork labWork = session.find(LabWork.class, id);
        session.delete(labWork);

        System.out.println(1234);
        //System.out.println(labWork);
        tx.commit();
        coordinateSevice.delete(labWork.getCoordinates());
        personService.delete(labWork.getPerson());
        locationService.delete(labWork.getPerson().getLocation());
        disciplineService.delete(labWork.getDiscipline());

    }
    public LabWork findUsername(Long id){
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        LabWork labWork = session.find(LabWork.class, id);

        System.out.println(1234);
        //System.out.println(labWork);
        tx.commit();
        return labWork;
    }

    public LabWork findByMinimalPoint(long minimalPoint){
        Session session = sessionFactory.openSession();
        String hql = "FROM LabWork l WHERE l.minimalPoint = :minimalPoint";
        Query<LabWork> queue=session.createQuery(hql, LabWork.class);
        var q =queue.setParameter("minimalPoint", minimalPoint);
        System.out.println(q.list().size());
        if(!q.list().isEmpty()){
            return q.list().get(0);
        } else{
            return null;
        }
    }
    public List<LabWork> groupById(FilterByIdDTO filterByIdDTO){
        Session session = sessionFactory.openSession();
        String hql = "FROM LabWork l WHERE l.id >= :min and l.id<=:max";
        Query<LabWork> queue=session.createQuery(hql, LabWork.class);
        queue.setParameter("min", filterByIdDTO.getMin());
        queue.setParameter("max",filterByIdDTO.getMax());
        return queue.list();
    }
}
