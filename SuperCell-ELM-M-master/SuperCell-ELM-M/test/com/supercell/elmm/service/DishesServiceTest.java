package com.supercell.elmm.service;

import static org.junit.Assert.*;
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
import com.supercell.elmm.dao.DishesDao;
import com.supercell.elmm.entity.Dishes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DishesServiceTest {
	@Resource(name="dishService")
	DishesService dishService;
	@Resource(name="dishesDao")
	DishesDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

//	@Test
	public void testAddDish() {
		Dishes dishes = new Dishes(1, "红烧肉123", "test", 13, true, 10);
		boolean result = dishService.addDish(dishes);
		Assert.assertTrue(result);
	}

//	@Test
	public void testDeleteDish() {
		boolean result = dishService.deleteDish(117);
		Assert.assertTrue(result);
	}

//	@Test
	public void testUpdateDish() {
		Dishes dishes = new Dishes(1, "红烧肉123", "test", 13, true, 50);
		dishes.setId(116);
		boolean result = dishService.updateDish(dishes);
		Assert.assertTrue(result);
	}

//	@Test
	public void testFindDishById() throws JsonProcessingException {
		Dishes dishes = dishService.findDishById(117);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dishes));
	}

	@Test
	public void testQueryDishesByMerchant() throws JsonProcessingException {
		List<Dishes> dishes = dishService.queryDishesByMerchant(1);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(dishes));
	}

}
