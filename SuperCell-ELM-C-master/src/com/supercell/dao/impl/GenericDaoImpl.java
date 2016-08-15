package com.supercell.dao.impl;

import com.supercell.dao.GenericDao;
import com.supercell.entity.GenericEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.NativeQuery;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by WUJO2 on 8/6/2016.
 */
public abstract class GenericDaoImpl<T extends GenericEntity> implements GenericDao<T> {
    @Resource
    private SessionFactory sessionFactory;

    abstract Class<T> classOfT();

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T get(Integer id) {
        return (T) getSession().get(classOfT(), id);
    }

    @Override
    public List<T> getAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classOfT());
        return search(detachedCriteria, null, null);
    }

    @Override
    public Serializable save(T t) {
        return getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void merge(T t) {
        getSession().merge(t);
    }

    @Override
    public void persist(T t) {
        getSession().persist(t);
    }

    @Override
    public void remove(T t) {
        getSession().delete(t);
    }

    @Override
    public void remove(Integer id) {
        getSession().delete(get(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> search(DetachedCriteria dc, Integer start, Integer size) {
        dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        Criteria criteria = dc.getExecutableCriteria(getSession());
        if (start != null) {
            criteria.setFirstResult(start);
        }
        if (size != null) {
            criteria.setMaxResults(size);
        }
        return criteria.list();
    }

    @Override
    public int count(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(getSession()).setProjection(Projections.rowCount());
        return (int) criteria.uniqueResult();
    }

    @Override
    public List<T> search(DetachedCriteria dc) {
        return search(dc, null, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> get(Integer start, Integer size) {
        return getSession().createQuery("from " + classOfT().getSimpleName())
                .setFirstResult(start)
                .setMaxResults(size)
                .list();
    }

    @Override
    public NativeQuery createSQLQuery(String sql) {
        return getSession().createNativeQuery(sql);
    }

    @Override
    public Query createQuery(String hql) {
        return getSession().createQuery(hql);
    }
}
