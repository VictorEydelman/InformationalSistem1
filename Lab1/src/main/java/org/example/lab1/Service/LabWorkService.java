package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.ResponceFormate.filterGroupByIdResponce;
import org.example.lab1.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LabWorkService {
    private final HistoryLabWorkService historyLabWorkService;
    private final HistoryCoordinateSevice historyCoordinateSevice;

    @Autowired
    private SessionFactory sessionFactory;

    public void add(LabWork labWork){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(labWork);
        tx.commit();
    }

    public List<LabWork> getAll(){
        Session session = sessionFactory.openSession();
        Query<LabWork> query = session.createQuery("FROM LabWork l",LabWork.class);
        return query.list();
    }

    public void update(LabWork labWork, String username){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(labWork);
        tx.commit();
    }
    public void delete(Long id, String username, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        LabWork labWork = session.find(LabWork.class, id);
        session.delete(labWork);
        tx.commit();
        if(countByPerson(labWork.getCoordinates().getId())==1) {
            coordinateSevice.delete(labWork.getCoordinates());
        }
        if(countByPerson(labWork.getPerson().getId())==1){
            personService.delete(labWork.getPerson());
        }
        if(countByLocation(labWork.getPerson().getLocation().getId())==1) {
            locationService.delete(labWork.getPerson().getLocation());
        }
        if(countByDiscipline(labWork.getDiscipline().getId())==1) {
            disciplineService.delete(labWork.getDiscipline());
        }
    }

    public LabWork findById(Long id){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        LabWork labWork = session.find(LabWork.class, id);
        tx.commit();
        return labWork;
    }

    public LabWork findByMinimalPoint(long minimalPoint){
        Session session = sessionFactory.openSession();
        String hql = "FROM LabWork l WHERE l.minimalPoint = :minimalPoint";
        Query<LabWork> queue=session.createQuery(hql, LabWork.class);
        var q =queue.setParameter("minimalPoint", minimalPoint);
        if(!q.list().isEmpty()){
            return q.list().get(0);
        } else{
            return null;
        }
    }
    public List<filterGroupByIdResponce> groupById(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select id, count(*) FROM LabWork GROUP BY id ORDER BY id";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<filterGroupByIdResponce> resultList = new ArrayList<>();
        List<Object[]> results = query.list();
        for (Object[] row : results) {
            Long id = (Long) row[0];
            Long count = (Long) row[1];
            resultList.add(new filterGroupByIdResponce(id, count));
        }
        tx.commit();
        return resultList;
    }
    public List<LabWork> getByDescription(String description){
        Session session = sessionFactory.openSession();
        Query<LabWork> queue = session.createQuery(
                "FROM LabWork l where l.description LIKE :description", LabWork.class);
        queue.setParameter("description", description+"%");
        return queue.list();
    }
    public int countByCoordinates(int id){
        Session session = sessionFactory.openSession();
        Query<LabWork> queue = session.createQuery(
                "FROM LabWork l where l.coordinates.id = :id", LabWork.class);
        queue.setParameter("id",id);
        return queue.list().size();
    }
    public int countByDiscipline(int id){
        Session session = sessionFactory.openSession();
        Query<LabWork> queue = session.createQuery(
                "FROM LabWork l where l.discipline.id = :id", LabWork.class);
        queue.setParameter("id",id);
        return queue.list().size();
    }
    public int countByPerson(int id){
        Session session = sessionFactory.openSession();
        Query<LabWork> queue = session.createQuery(
                "FROM LabWork l where l.person.id = :id", LabWork.class);
        queue.setParameter("id",id);
        return queue.list().size();
    }
    public int countByLocation(int id){
        Session session = sessionFactory.openSession();
        Query<LabWork> queue = session.createQuery(
                "FROM LabWork l where l.person.location.id = :id", LabWork.class);
        queue.setParameter("id",id);
        return queue.list().size();
    }
}
