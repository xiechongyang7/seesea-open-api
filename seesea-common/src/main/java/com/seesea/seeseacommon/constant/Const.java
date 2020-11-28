package com.seesea.seeseacommon.constant;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/26 下午 9:35
 * @Author xiechongyang
 */
public class Const {

    /*************************登录使用 start*************************/
    /**
     * 成功
     */
    public static String REGISTER_SUCCESS = "1";
    /**
     * 失败
     */
    public static String REGISTER_FAIL = "0";
    /**
     * 邮箱未认证
     */
    public static String REGISTER_EMAILNOCHECK = "2";

    /**
     * 手机
     */
    public static String REGISTER_TYPE_PHONE = "phone";
    /**
     * 邮箱
     */
    public static String REGISTER_TYPE_EMIAL = "email";
    /**
     * 三方登录-github
     */
    public static String REGISTER_TYPE_GITHUB = "gitHub";


    /**
     * 成功
     */
    public static String LOGI_SUCCESS = "1";
    /**
     * 失败
     */
    public static String LOGI_FAIL = "0";


    /*************************登录使用 end*************************/
    /**
     * 验证码长度
     */
    public static String CODE_LEN = "4";

    /**
     * 200 通用成功码
     */
    public static String SUCCESS = "200";
    public static int SUCCESS_INT = 200;

    /**
     * 短信类型
     */
    //发送验证码
    public static String SEND = "1";
    //发送通知
    public static String NOTICE = "2";

    /**
     * 短信状态
     */
    //已经发送
    public static String ALREADY_SEND = "1";
    //发送失败
    public static String FAIL_SEND = "2";
    //已经完成
    public static String COMPLETE = "3";

    //短信发送校验状态
    public static String SUCCESS_SEND = "1";
    public static String SEND_FAIL = "2";
    public static String SUCCESS_CHECK = "1";
    public static String CHECK_FAIL = "2";

    /**
     * 短信发送校验信息
     */
    public static String CHECK_MSG = "check success";


}
