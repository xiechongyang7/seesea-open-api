package com.seesea.seeseasms.entity.vo;


import com.seesea.seeseacommon.base.ReqVoCommon;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 上午 12:02
 * @Author xiechongyang
 */
public class CheckSmsVo extends ReqVoCommon {

//    @Type(notNull = true, type = "PHONE")
    private String phone;

//    @Type(notNull = true, type = "VCODE")
    private String vcode;

//    @Allow(notNull = true, allows = {"1"})
    private String workType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
