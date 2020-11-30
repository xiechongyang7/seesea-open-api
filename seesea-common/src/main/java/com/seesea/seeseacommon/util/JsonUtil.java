package com.seesea.seeseacommon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @Description json工具类
 * @Since JDK1.8
 * @Createtime 17:33 2018/9/14
 * @Author 谢重阳
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * json转对象
     *
     * @param <T>
     * @param str
     * @param clazz
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T jsonToObj(String str, Class<T> clazz) throws IOException {
        try {
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String jsonAddParam(String obj, String key, Object value) throws IOException {
        String str = null;
        Map<String, Object> map = jsonToObj(obj, Map.class);
        map.put(key, value);
        str = objToJson(map);
        return str;
    }

    public static void main(String[] arg) throws IOException {
        Map map = jsonToObj("{\n" +
                "    \"reqId\":\"1\",\n" +
                "    \"serviceId\":\"1100001\",\n" +
                "    \"reqTime\":\"2018-09-16 08:00:00\",\n" +
                "    \"sign\":false,\n" +
                "    \"isSign\":false,\n" +
                "    \"isEncrypt\":false,\n" +
                "    \"userId\":\"1\",\n" +
                "    \"data\":\"11111\"\n" +
                "}", Map.class);
        String a = objToJson(map);
        System.out.println(a);
    }
}
