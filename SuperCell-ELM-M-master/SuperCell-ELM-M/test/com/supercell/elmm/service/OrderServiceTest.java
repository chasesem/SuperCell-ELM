package com.supercell.elmm.service;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.vo.OrderDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class OrderServiceTest {
	@Resource(name="orderService")
	OrderService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQueryAllOrderByMerchant() throws JsonProcessingException {
		List<OrderDetails> details = orderService.queryNewOrderByMerchant(150);
		System.out.println(details.size());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(details));
	}

//	@Test
	public void testFindOrderDetailsById() throws JsonProcessingException {
		OrderDetails details = orderService.findOrderDetailsById(104);
//		System.out.println(details.size());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(details));
	}

//	@Test
	public void testReceiveOrder() {
		CustomerOrder order = orderService.findOrderById(106);
		boolean result = orderService.receiveOrder(order);
		Assert.assertTrue(result);
	}

//	@Test
	public void testRefuseOrder() {
		CustomerOrder order = orderService.findOrderById(106);
		boolean result = orderService.refuseOrder(order);
		Assert.assertTrue(result);
	}

}
