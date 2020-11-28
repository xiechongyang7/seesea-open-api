package com.seesea.seeseacommon.constant;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/26 下午 9:04
 * @Author xiechongyang
 */
public class RedisKey {


    /**
     * 验证码类型的短信key
     */
    public static String PHONE_SEND_SMS = "SS:BIZ:SMS:%s";
    /**
     * 发送次数key
     */
    public static String PHONE_SEND_NUM = "SS:BIZ:SMSNUM:%s";
    public static String VCODE_CHECK_FILED = "CHECKNUM";
    public static String VCODE_FILED = "VCODE";
    public static String SEND_ID_FILED = "SENDID";

    /**
     * 发送次数的域
     */
    public static String PHONE_SEND_NUM_FILED = "SMSNUM";

}
