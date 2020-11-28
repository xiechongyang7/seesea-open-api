package com.seesea.seeseasms.entity.bo;

import java.util.Date;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/26 上午 12:28
 * @Author xiechongyang
 */
public class ChannelResultBo {

    private String vCode;
    private String msg;
    private Date sendDate;

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
