package com.supercell.dao;

import com.supercell.entity.GenericEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.NativeQuery;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends GenericEntity> {

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

    /**
     * 持久化对象，返回持久化对象的主键。
     *
     * @param t 需要被持久化的对象
     */
    Serializable save(T t);

    void update(T t);

    /**
     * 除非你理解JPA规范，否则不要用这个方法
     */
    void persist(T t);

    /**
     * 除非你理解JPA规范，否则不要用这个方法
     */
    void merge(T t);

    void remove(Integer id);

    void remove(T t);

    int count(DetachedCriteria dc);

    /**
     * 查找数据库，并返回结果，如果没有任何匹配的行，返回一个空的List而不是null.
     *
     * @param dc DetachedCriteria对象，匹配的条件
     */
    List<T> search(DetachedCriteria dc);

    /**
     * 查找数据库，并返回结果，如果没有任何匹配的行，返回一个空的List而不是null.
     *
     * @param dc    DetachedCriteria对象，匹配的条件
     * @param start 开始匹配的行下标
     * @param size  匹配的数量
     */
    List<T> search(DetachedCriteria dc, Integer start, Integer size);

    /**
     * 根据传入的参数，返回结果，如果没有任何匹配的行，返回一个空的List而不是null。
     *
     * @param start 开始匹配的行下标
     * @param size  匹配的数量
     */
    List<T> get(Integer start, Integer size);

    /**
     * 创建HQL语句，并执行查询，因为Session的问题，现在还不能使用
     *
     * @param hql HQL语句
     * @return Query对象，是JPA规范的对象
     */
    @Deprecated
    Query createQuery(String hql);

    /**
     * 创建SQL语句，并执行查询，因为Session的问题，现在还不能使用
     *
     * @param sql SQL语句
     */
    @Deprecated
    NativeQuery createSQLQuery(String sql);
}
