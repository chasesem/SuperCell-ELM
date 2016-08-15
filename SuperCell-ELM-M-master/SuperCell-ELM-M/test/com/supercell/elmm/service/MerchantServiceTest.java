package com.supercell.elmm.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.vo.MerchantState;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MerchantServiceTest {
	@Resource(name="merchantService")
	MerchantService merchantService;
	@Resource(name="producerService")
	private ProducerService producerService;
	@Resource(name="queueDestination")
	private Destination destination;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

//	@Test
	public void testLogin() {
		boolean result = merchantService.login("12345", "12345");
		Assert.assertTrue(result);
	}

	@Test
	public void testRegister() {
		Merchant merchant = new Merchant("333", "321", "一店", "testpath", "test", "test", "test", 10.0, 21);
		if (merchantService.findMerchantByPhoneNumber(merchant.getPhoneNumber())==null) {
			boolean result = merchantService.register(merchant);
//			Assert.assertTrue(result);
			if (result) {
				MerchantState merchantState = new MerchantState(merchant.getId(),MerchantState.AUDIT_MERCHANT_STATE);
				String message = JSONUtil.convertToJSON(merchantState);
				producerService.sendMessage(destination, message);
			}
		}
//		Assert.assertTrue(result);
	}

//	@Test
	public void testUpdateMerchant() {
		Merchant merchant = new Merchant("321", "321", "奶茶店", "testpath", "test", "test", "test", 10.0, 21);
		merchant.setId(122);
		boolean result = merchantService.updateMerchant(merchant);
		Assert.assertTrue(result);
	}

//	@Test
	public void testQueryAllMerchants() throws JsonProcessingException {
		List<Merchant> merchants = merchantService.queryAllMerchants();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(merchants));
	}

//	@Test
	public void testFindMerchantByID() throws JsonProcessingException {
		Merchant merchant  = merchantService.findMerchantByID(122);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(merchant));
	}

//	@Test
	public void testFindMerchantByPhoneNumber() throws JsonProcessingException {
		Merchant merchant  = merchantService.findMerchantByPhoneNumber("321");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(merchant));
	}

}
