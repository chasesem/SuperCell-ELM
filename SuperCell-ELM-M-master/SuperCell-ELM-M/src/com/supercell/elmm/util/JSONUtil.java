package com.supercell.elmm.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by WUJO2 on 7/22/2016.
 */
public class JSONUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JSONUtil() {
    }

    public static String convertToJSON(Object... objects) {
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static void writeJSONToFrontEnd(HttpServletResponse response, String jsonString) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(response.getWriter()));
            printWriter.write(jsonString);
            printWriter.flush();
            printWriter.close();
        } catch (IOException ignored) {
        }
    }
    
    public static Object jsonToObject(String input,Class clz){
    	try {
			return objectMapper.readValue(input, clz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
