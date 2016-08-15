package com.supercell.elmm.dao;

import java.util.List;
import javax.annotation.Resource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercell.elmm.dao.OrderItemDao;
import com.supercell.elmm.vo.OrderItemDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class OrderItemDaoTest {
	@Resource(name="orderItemDao")
	OrderItemDao orderItemDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQueryByOrderId() throws JsonProcessingException {
		List<OrderItemDetails> itemDetails = orderItemDao.queryByOrderId(101);
		System.out.println(itemDetails.size());
//		System.out.println(itemDetails.get(0).getDishesName());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(itemDetails));
	}

}
