package com.supercell.elmm.dao;


import java.util.List;

import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercell.elmm.dao.impl.MerchantDaoImpl;
import com.supercell.elmm.entity.Merchant;

import static org.hibernate.criterion.Restrictions.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MerchantDaoTest {
	@Resource(name="merchantDao")
	MerchantDao merchantDao;
	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void testFindMerchant() {
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Merchant.class);
//		detachedCriteria.add(eq(Merchant.PHONE_NUMBER, "12345"));
//		System.out.println(dao.search(detachedCriteria).get(0));
//	}

//	@Test
//	public void testAddMerchant() {
//		Merchant merchant = new Merchant("123", "123", "shopName", "shopPicPath", "licensePicPath", "idCardPicPath", "address", 2.0, 12);
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
//	    detachedCriteria.add(eq(Customer.PHONE_NUMBER, "13560479729"));
//		dao.save(merchant);
//	}
	
	@Test
	public void testQueryAll() throws JsonProcessingException, ClassNotFoundException{
//		List<Merchant> merchants = merchantDao.queryAll();
////		System.out.println(merchantDao.get(1).getPhoneNumber());
////		System.out.println(merchantDao.createQuery("from Merchant").size());
//		System.out.println(merchants.size());
//		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(mapper.writeValueAsString(merchants));
////		boolean result = merchantDao.findMerchant("12345", "12345");
////		Assert.assertTrue(result);
////		Merchant merchant = new Merchant("lsy", "lsy", "shopName", "shopPicPath", "licensePicPath", "idCardPicPath", "address", 2.0, 12);
////		merchantDao.addMerchant(merchant);
			Class.forName("com.supercell.elmm.util.ClientFactory");
	}

}
