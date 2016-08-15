package com.supercell.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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

    // 因为Jackson转化的JSON字符串为数组，因此传入的class应该为对象数组的class
    public static Object convertToObject(String jsonString, Class objectArrayClass) {
        try {
            return objectMapper.readValue(jsonString, objectArrayClass);
        } catch (IOException e) {
            return null;
        }
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
}
