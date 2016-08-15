package com.supercell.elma.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	public static String objetc2Json(Object obj) throws JsonProcessingException{
		return mapper.writeValueAsString(obj);
	}
	
	public static Object json2Object(String json,Class clz) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return mapper.readValue(json, clz);
	}
}
