package com.seesea.seeseacommon.util;



import java.util.Date;

/**
 * @Description id生成规则 业务id(2) + 简化时间(14) + 四位随机数(4) = id(20)
 * @Since JDK1.8
 * @Createtime 2018/10/26 下午 10:33
 * @Author xiechongyang
 */
public class IdGenerate {



    public static String REQ_ID = "10";
    public static String SMS_ID = "11";

    private static String getFourRandom() {
        return String.valueOf((int) (Math.random() * 9 + 1) * 1000);
    }



    public static String getId(String head) {
        String data = DateUtils.toSimpleFullTimeStr(new Date());
        return head + data + getFourRandom();
    }
}
