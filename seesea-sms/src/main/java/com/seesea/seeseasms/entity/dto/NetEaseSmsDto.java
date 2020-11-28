package com.seesea.seeseasms.entity.dto;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/26 上午 12:16
 * @Author xiechongyang
 */
public class NetEaseSmsDto {

    /**
     * 是	目标手机号，非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)
     */
    private String mobile;
    /**
     * 否	目标设备号，可选参数
     */
//    private String deviceId;
    /**
     * 否	模板编号(如不指定则使用配置的默认模版)
     */
    private int templateid;
    /**
     * 否	验证码长度，范围4～10，默认为4
     */
    private String codeLen;
    /**
     * 否	客户自定义验证码，长度为4～10个数字 如果设置了该参数，则codeLen参数无效
     */
    private int authCode;

    /**
     * 否	是否需要支持短信上行。true:需要，false:不需要 说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
     */
//    private Boolean needUp;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
//
//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }

    public int getTemplateid() {
        return templateid;
    }

    public void setTemplateid(int templateid) {
        this.templateid = templateid;
    }

    public String getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(String codeLen) {
        this.codeLen = codeLen;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

//    public Boolean getNeedUp() {
//        return needUp;
//    }
//
//    public void setNeedUp(Boolean needUp) {
//        this.needUp = needUp;
//    }
}
