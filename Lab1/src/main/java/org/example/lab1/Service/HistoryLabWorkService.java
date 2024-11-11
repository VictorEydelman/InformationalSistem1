package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.ResponceFormate.FilterGroupByIdResponce;
import org.example.lab1.entities.HistoryLabWorks;
import org.example.lab1.entities.LabWork;
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
public class HistoryLabWorkService {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(HistoryLabWorks historyLabWork){
        System.out.println(historyLabWork);
        System.out.println(historyLabWork.getDiscipline());
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //user.setId(1L);
        //System.out.print(session);
        System.out.println(historyLabWork);

        session.merge(historyLabWork);
        tx.commit();
    }

    public List<HistoryLabWorks> getAll(){
        Session session = sessionFactory.openSession();
        //Query<LabWorkResponce> queue = session.createQuery(
            //    "FROM LabWork l JOIN " +
          //      "l.coordinates c JOIN l.discipline JOIN l.person p JOIN p.location", LabWorkResponce.class);
        Query<HistoryLabWorks> query = session.createQuery("FROM HistoryLabWorks l",HistoryLabWorks.class);
        return query.list();
    }

    public void update(HistoryLabWorks historyLabWork){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(historyLabWork);
        tx.commit();
    }
    public void delete(Long id,HistoryCoordinateSevice coordinateSevice, HistoryDisciplineService disciplineService, HistoryPersonService personService, HistoryLocationService locationService){

        //user.setId(1L);
        //System.out.println(labWork);
        //System.out.print(session);
        System.out.println(id);
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        HistoryLabWorks historyLabWork = session.find(HistoryLabWorks.class, id);
        session.delete(historyLabWork);

        System.out.println(1234);
        //System.out.println(labWork);
        tx.commit();
        coordinateSevice.delete(historyLabWork.getCoordinates());
        personService.delete(historyLabWork.getPerson());
        locationService.delete(historyLabWork.getPerson().getLocation());
        disciplineService.delete(historyLabWork.getDiscipline());
    }

    public LabWork findById(Long id){
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
    public List<FilterGroupByIdResponce> groupById(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "select id, count(*) FROM LabWork GROUP BY id ORDER BY id";
        Query<Object[]> queue=session.createQuery(hql, Object[].class);
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<FilterGroupByIdResponce> resultList = new ArrayList<>();
        List<Object[]> results = query.list();

        for (Object[] row : results) {
            Long id = (Long) row[0];
            Long count = (Long) row[1];
            resultList.add(new FilterGroupByIdResponce(id, count));
        }
        System.out.println();
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
