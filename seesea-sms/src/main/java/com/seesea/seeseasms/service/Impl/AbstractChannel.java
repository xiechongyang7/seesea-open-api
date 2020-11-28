package com.seesea.seeseasms.service.Impl;


import com.seesea.seeseacommon.base.BaseService;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;
import com.seesea.seeseacommon.util.HttpUtils;
import com.seesea.seeseasms.entity.SmsChannel;
import com.seesea.seeseasms.entity.SmsLog;
import com.seesea.seeseasms.entity.bo.ChannelResultBo;
import com.seesea.seeseasms.entity.vo.SendSmsVo;
import com.seesea.seeseasms.mapper.SmsLogMapper;
import com.seesea.seeseasms.service.ISmsChannel;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 下午 11:36
 * @Author xiechongyang
 */
public abstract class AbstractChannel extends BaseService implements ISmsChannel {


    @Autowired
    private SmsLogMapper smsLogPoMapper;

    /**
     * 校验类
     *
     * @param sendSmsVo
     * @param smsChannelPo
     * @return
     */
    public ChannelResultBo send(SendSmsVo sendSmsVo, SmsChannel smsChannelPo) throws BizException {
        throw new BizException(ResultCode.ER_1200);
    }

    /**
     * 通知类
     *
     * @param sendSmsVo
     * @param smsChannelPo
     * @return
     */
    public ChannelResultBo notice(SendSmsVo sendSmsVo, SmsChannel smsChannelPo) throws BizException {
        throw new BizException(ResultCode.ER_1200);
    }

    /**
     * 语音类
     *
     * @param sendSmsVo
     * @param smsChannelPo
     * @return
     */
    public ChannelResultBo voice(SendSmsVo sendSmsVo, SmsChannel smsChannelPo) throws BizException {
        throw new BizException(ResultCode.ER_1200);
    }

    /**
     * 请求日志入库
     *
     * @param smsLogPo
     */
    public void updateSmsLog(SmsLog smsLogPo) {
        smsLogPoMapper.insert(smsLogPo);
    }

    /**
     * 生成4位验证码
     *
     * @return
     */
    public int getVCode() {
        return (int) ((Math.random() * 9 + 1) * 1000);
    }


    /**
     * map 类型请求
     *
     * @param url
     * @param params
     * @return
     */
    public byte[] doPost(String url, Map params, List<Header> headers) {

        return HttpUtils.doPost(url, params, "HTTPS", headers);

    }

    /**
     * map 类型请求
     *
     * @param url
     * @param params
     * @return
     */
    public byte[] doPost(String url, Map params) {

        return HttpUtils.doPost(url, params, "HTTPS");

    }

    /**
     * json字符串请求
     *
     * @param url
     * @param params
     * @return
     */
    public byte[] doPost(String url, String params) {

        return HttpUtils.doPost(url, params, "HTTPS");
    }
}
