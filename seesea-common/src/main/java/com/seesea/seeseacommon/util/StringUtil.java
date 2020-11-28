package com.seesea.seeseacommon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description 常用str工具类
 * @Since JDK1.8
 * @Createtime 2018/9/14 下午 11:56
 * @Author xiechongyang
 */
public class StringUtil {


    /**
     * 是不是空字符串
     *
     * @param str 判断的字符
     * @return 是空返回true
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str) || str.length() == 0;
    }

    /**
     * @param obj
     * @return
     * @description 参数排序
     */
    public static String sortValue(Object obj) {
        String str = null;
        try {
            if (obj == null) {
                return str;
            }
            Class<?> clz = obj.getClass();
            Field[] declaredFields = clz.getDeclaredFields();
            List<String> list = new ArrayList<String>();
            for (Field field : declaredFields) {
                String name = field.getName();
                if ("SIGN".equals(name.toUpperCase()) || "THIS$0".equals(name.toUpperCase()) || "SERIALVERSIONUID".equals(name.toUpperCase())) {
                    continue;
                }
                String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Method declaredMethod = clz.getMethod(methodName);
                Object fieldValue = declaredMethod.invoke(obj);
                if (null == fieldValue || "".equals(fieldValue)) {
                    continue;
                }
                list.add(name + "=" + fieldValue.toString());
            }
            if (list.size() == 0) {
                return str;
            }
            Collections.sort(list);
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                stringBuilder.append("&").append(list.get(i));
            }
            str = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


}
