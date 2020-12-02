package com.seesea.seeseacommon.base;


import com.seesea.seeseacommon.common.ResultCode;

import java.util.Map;

/**
 * @Description 业务处理返回map set到data中
 * @Since JDK1.8
 * @Createtime 2018/9/16 下午 4:01
 * @Author xiechongyang
 */
public class Result {

    private String code;

    private String msg;

    private String reqId;

    private String sequenceId;

    private String accountId;

    private Map<String,Object> data;

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


    public Map<String,Object> getData() {
        return data;
    }

    public void setData(Map<String,Object> data) {
        try {
            if (data.get("code") != null && !"".equals(data.get("code"))) {
                setCode(data.get("code").toString());
                setMsg(data.get("msg").toString());
            } else {
                if (data.get("msg") != null && !"".equals(data.get("msg"))) {
                    setMsg(data.get("msg").toString());
                } else {
                    setCode(ResultCode.SUCCESS.code);
                }
            }
            this.data = data;
        } catch (Exception e) {
            setCode(ResultCode.ERR.code);
            setMsg(String.format(ResultCode.ERR.msg, "业务层返回参数有误"));
            this.data = data;
        }
    }
}
