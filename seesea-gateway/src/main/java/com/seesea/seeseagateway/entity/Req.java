package com.seesea.seeseagateway.entity;


/**
* @description
* @since JDK1.8
* @createTime 2020/11/19 19:40
* @author xie
*/
public class Req {

    private String reqId;
    private String sequenceId;
    private String accountId;
    private String sign;
    private String serviceId;
    private String data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

