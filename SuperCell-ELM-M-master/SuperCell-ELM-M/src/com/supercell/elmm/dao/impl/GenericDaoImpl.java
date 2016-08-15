package com.supercell.elmm.dao.impl;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.supercell.elmm.dao.GenericDao;
import com.supercell.elmm.entity.GenericEntity;


public abstract class GenericDaoImpl<T extends GenericEntity> implements GenericDao<T> {
	@PersistenceContext(name="un")
	private EntityManager em;

    abstract Class classOfT();
    
    public Session getSession(){
    	Session session = (Session) em.getDelegate();
    	return session;
    }
    
    public CriteriaBuilder getCriteriaBuilder(){
    	return em.getCriteriaBuilder();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Integer id) {
    	return (T) em.find(classOfT(), id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> getAll() {
    	
    	return em.createQuery("from " + classOfT().getSimpleName())
                .getResultList();
    }
    
    public boolean update(T t){
    	try {
    		em.merge(t);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }

    @Override
    public boolean persist(T t) {
    	try {
    		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    		em.persist(t);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }

    @Override
    public boolean remove(T t) {
    	try {
    		em.remove(t);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }

    @Override
    public boolean remove(Integer id) {
    	try {
    		em.remove(get(id));
		} catch (Exception e) {
			return false;
		}
    	return true;
    	
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> search(CriteriaQuery cq, Integer start, Integer size) {
    	Query query = em.createQuery(cq);
	    if (start != null) {
	    	query.setFirstResult(start);
		}
		if (size != null) {
			query.setMaxResults(size);
		}
    	
        return query.getResultList();
    }

    @Override
    public List<T> search(CriteriaQuery cq) {
        return search(cq, null, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> get(Integer start, Integer size) {
        return em.createQuery("from " + classOfT().getSimpleName())
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

	@Override
    public Query createQuery(String jpql) {
        return em.createQuery(jpql);
    }
	
	public Query createSQLQuery(String sql){
		return em.createNativeQuery(sql);
	}
	
	public Query createSQLQuery(String sql,Class clz){
		return em.createNativeQuery(sql, clz);
	}
}
