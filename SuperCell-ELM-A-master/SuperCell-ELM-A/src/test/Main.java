package test;
import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) {
		String json = "[{\"customerId\":4,\"phoneNumber\":\"13726261319\",\"merchantId\":1,\"merchantName\":\"shop_name\",\"orderId\":104,\"complainMessage\":\"a test\"}]";
		try {
			//Complaint c = (Complaint) JSONUtil.json2Object(json, Complaint.class);
			ObjectMapper mapper = new ObjectMapper();
//			Complaint c = mapper.readValue(json, Complaint[].class)[0];
			//Complaint cd = new Complaint();
//			cd.setCustomerID(customerID);
//			System.out.println(c.getOrderId());
//			System.out.println(c.getComplainMessage());
			System.out.println(mapper.writeValueAsString(new Date()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
