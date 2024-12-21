package com.ozhegov.laba3.dao;

import com.ozhegov.laba3.model.Point;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.model.LazyDataModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@Named("pointDAO")
@ApplicationScoped
public class PointDAO implements DAO<Point>{
    @Override
    public void save(Point point) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(point);
            session.getTransaction().commit();
        }
    }
    @Override
    public List<Point> getAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Point> criteriaQuery = cb.createQuery(Point.class);

            Root<Point> root = criteriaQuery.from(Point.class);
            criteriaQuery.select(root);

            Query<Point> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        }
    }
}
