package com.seesea.seeseagateway.entity;


import java.util.Date;
import java.util.Map;

/**
* @description
* @since JDK1.8
* @createTime 2020/11/23 19:22
* @author xie
*/
public class Rsp {

    private String code;

    private String msg;

    private String reqId;

    private String sequenceId;

    private String accountId;

    private Map<String,Object> data;

    private Date rspTime = new Date();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Date getRspTime() {
        return rspTime;
    }

    public void setRspTime(Date rspTime) {
        this.rspTime = rspTime;
    }
}
