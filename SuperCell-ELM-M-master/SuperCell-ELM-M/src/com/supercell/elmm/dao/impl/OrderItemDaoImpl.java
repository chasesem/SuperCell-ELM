package com.supercell.elmm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.OrderItemDao;
import com.supercell.elmm.entity.OrderItem;
import com.supercell.elmm.vo.OrderDetails;
import com.supercell.elmm.vo.OrderItemDetails;


@Repository("orderItemDao")
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem> implements OrderItemDao {
    @Override
    Class classOfT() {
        return OrderItem.class;
    }

	@Override
	public List<OrderItemDetails> queryByOrderId(int orderId) {
		String jpql = "select new com.supercell.elmm.vo.OrderItemDetails(d.id,d.merchantId,d.dishesName,d.dishesPicPath,d.price,d.avaliable,d.stock,i.count) " +
                "from OrderItem i,Dishes d where d.id=i.dishesId and i.orderId="+orderId;
		Query query = createQuery(jpql);
		List<OrderItemDetails> itemDetails = query.getResultList();
		return itemDetails;
	}
}
