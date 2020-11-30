package com.seesea.seeseacommon.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seesea.seeseacommon.common.ResultCode;


/**
 * @Description 所有请求vo都必须继承此类
 * @Since JDK1.8
 * @Createtime 2018/9/16 下午 4:00
 * @Author xiechongyang
 */
public class ReqVoCommon {

    private String reqId;
    private String sequenceId;
    private String accountId;

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

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @JsonIgnore
    public Result getResult() {
        Result result = new Result();
        result.setReqId(accountId);
        result.setSequenceId(sequenceId);
        result.setAccountId(reqId);
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        return result;
    }

}
