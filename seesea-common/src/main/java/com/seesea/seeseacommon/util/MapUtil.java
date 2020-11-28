package com.seesea.seeseacommon.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Description map工具类
 * @Since JDK1.8
 * @Createtime 2018/9/15 下午 6:47
 * @Author xiechongyang
 */
public class MapUtil {


    public static Map<String, String> BeanToMap(Object obj) {
        try {
            Map map = BeanUtils.describe(obj);
            map.remove("class");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
