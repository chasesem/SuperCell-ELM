package com.supercell.elmm.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.supercell.elmm.entity.GenericEntity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface GenericDao<T extends GenericEntity> {
	CriteriaBuilder getCriteriaBuilder();

    /**
     * 根据id取得行记录，如果没有对应的记录，返回null
     *
     * @param id 行记录的主键
     */
    T get(Integer id);
    /**
     * 取得数据库中所有的实体类的记录，不建议在数据量比较大的情况下使用。如果没有任何行记录，返回空List而不是null.
     */
    List<T> getAll();

    boolean update(T t);

    /**
     * 除非你理解JPA规范，否则不要用这个方法
     */
    boolean persist(T t);

    boolean remove(Integer id);

    boolean remove(T t);

//    int count(DetachedCriteria dc);

    /**
     * 查找数据库，并返回结果，如果没有任何匹配的行，返回一个空的List而不是null.
     *
     * @param dc DetachedCriteria对象，匹配的条件
     */
    List<T> search(CriteriaQuery dc);

    /**
     * 查找数据库，并返回结果，如果没有任何匹配的行，返回一个空的List而不是null.
     *
     * @param dc    DetachedCriteria对象，匹配的条件
     * @param start 开始匹配的行下标
     * @param size  匹配的数量
     */
    List<T> search(CriteriaQuery dc, Integer start, Integer size);

    /**
     * 根据传入的参数，返回结果，如果没有任何匹配的行，返回一个空的List而不是null。
     * 
     * @param start 开始匹配的行下标
     * @param size  匹配的数量
     */
    List<T> get(Integer start, Integer size);

    /**
     * 创建HQL语句，并执行查询
     *
     * @param hql HQL语句
     * @return Query对象，是JPA规范的对象
     */
//    @Deprecated
    Query createQuery(String jpql);
//
//    /**
//     * 创建SQL语句，并执行查询
//     *
//     * @param sql SQL语句
//     */
    Query createSQLQuery(String sql);
    Query createSQLQuery(String sql,Class clz);
}
