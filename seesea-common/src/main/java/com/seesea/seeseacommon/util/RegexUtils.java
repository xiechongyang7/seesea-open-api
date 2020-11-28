package com.seesea.seeseacommon.util;

import java.util.regex.Pattern;

/**
 * @Description 正则校验
 * @Since JDK1.8
 * @Createtime 2018/10/24 下午 11:33
 * @Author xiechongyang
 */
public class RegexUtils {

    /**
     * 是手机号返回true
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean isPhone(String content) {
        String restring = "^((13|14|15|17|18)[0-9]{1}\\d{8})$";
        return Pattern.matches(restring, content);
    }

    /**
     * 是邮箱返回true
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean isEmail(String content) {
        String restring = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.matches(restring, content);
    }

    /**
     * 是验证码返回true
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean isVCode(String content) {
        String restring = "(\\d{4})";
        return Pattern.matches(restring, content);
    }

    /**
     * 是汉字返回true
     *
     * @param content
     * @return
     */
    public static boolean isHanZi(String content) {
        String restring = "([\\u4e00-\\u9fa5]{2,4})";
        return Pattern.matches(restring, content);
    }

    public static void main(String arg[]) {

        System.out.println(isPhone("15552897461"));
        System.out.println(isEmail("x@1.com"));
        System.out.println(isVCode("11111"));
        System.out.println(isHanZi("哈哈"));

    }
}
