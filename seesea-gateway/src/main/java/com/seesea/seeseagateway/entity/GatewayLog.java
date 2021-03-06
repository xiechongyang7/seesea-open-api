package com.seesea.seeseagateway.entity;

import java.io.Serializable;
import java.util.Date;

public class GatewayLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.req_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String reqId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.sequence_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String sequenceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.accout_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String accoutId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.app_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.service_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.account_req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private Date accountReqTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private Date reqTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.rsp_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private Date rspTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.err_code
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String errCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gateway_log.err_msg
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private String errMsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gateway_log
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.req_id
     *
     * @return the value of gateway_log.req_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.req_id
     *
     * @param reqId the value for gateway_log.req_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.sequence_id
     *
     * @return the value of gateway_log.sequence_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getSequenceId() {
        return sequenceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.sequence_id
     *
     * @param sequenceId the value for gateway_log.sequence_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.accout_id
     *
     * @return the value of gateway_log.accout_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getAccoutId() {
        return accoutId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.accout_id
     *
     * @param accoutId the value for gateway_log.accout_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setAccoutId(String accoutId) {
        this.accoutId = accoutId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.app_id
     *
     * @return the value of gateway_log.app_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.app_id
     *
     * @param appId the value for gateway_log.app_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.service_id
     *
     * @return the value of gateway_log.service_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.service_id
     *
     * @param serviceId the value for gateway_log.service_id
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.account_req_time
     *
     * @return the value of gateway_log.account_req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public Date getAccountReqTime() {
        return accountReqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.account_req_time
     *
     * @param accountReqTime the value for gateway_log.account_req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setAccountReqTime(Date accountReqTime) {
        this.accountReqTime = accountReqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.req_time
     *
     * @return the value of gateway_log.req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.req_time
     *
     * @param reqTime the value for gateway_log.req_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.rsp_time
     *
     * @return the value of gateway_log.rsp_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public Date getRspTime() {
        return rspTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.rsp_time
     *
     * @param rspTime the value for gateway_log.rsp_time
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setRspTime(Date rspTime) {
        this.rspTime = rspTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.err_code
     *
     * @return the value of gateway_log.err_code
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.err_code
     *
     * @param errCode the value for gateway_log.err_code
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gateway_log.err_msg
     *
     * @return the value of gateway_log.err_msg
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gateway_log.err_msg
     *
     * @param errMsg the value for gateway_log.err_msg
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Wed Dec 02 20:31:38 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reqId=").append(reqId);
        sb.append(", sequenceId=").append(sequenceId);
        sb.append(", accoutId=").append(accoutId);
        sb.append(", appId=").append(appId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", accountReqTime=").append(accountReqTime);
        sb.append(", reqTime=").append(reqTime);
        sb.append(", rspTime=").append(rspTime);
        sb.append(", errCode=").append(errCode);
        sb.append(", errMsg=").append(errMsg);
        sb.append("]");
        return sb.toString();
    }
}