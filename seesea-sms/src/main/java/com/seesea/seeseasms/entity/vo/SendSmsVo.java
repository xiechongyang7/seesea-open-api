package com.seesea.seeseasms.entity.vo;

import com.seesea.seeseacommon.base.ReqVoCommon;
import com.seesea.seeseacommon.util.param.annotation.Allow;
import com.seesea.seeseacommon.util.param.annotation.Type;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/24 下午 11:23
 * @Author xiechongyang
 */
public class SendSmsVo extends ReqVoCommon {

    @Type(notNull = true, type = "PHONE")
    private String phone;

    @Allow(notNull = true, allows = {"1"})
    private String workType;

    private String userId;

    private String smsId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}
