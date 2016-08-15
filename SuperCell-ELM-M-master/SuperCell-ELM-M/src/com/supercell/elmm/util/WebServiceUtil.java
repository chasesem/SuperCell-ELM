package com.supercell.elmm.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class WebServiceUtil {
	public static Object getValue(Client client,String destination,Class clz){
		WebTarget taget=client.target(destination);
		Response response=taget.request().get();
		Object object=response.readEntity(clz);
		response.close();
		return object;
	}
}
