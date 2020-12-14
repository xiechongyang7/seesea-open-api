package com.seesea.seeseafegin.entity;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/14 16:45
 */
public class BillingVo {

    private String reqId;
    private String accountId;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "BillingVo{" +
                "reqId='" + reqId + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
