package com.seesea.seeseasms.service.Impl;


import com.seesea.seeseacommon.asserts.Assert;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;
import com.seesea.seeseacommon.constant.Const;
import com.seesea.seeseacommon.util.JsonUtil;
import com.seesea.seeseacommon.util.MapUtil;
import com.seesea.seeseasms.entity.SmsChannel;
import com.seesea.seeseasms.entity.SmsLog;
import com.seesea.seeseasms.entity.bo.ChannelResultBo;
import com.seesea.seeseasms.entity.dto.NetEaseSmsDto;
import com.seesea.seeseasms.entity.vo.SendSmsVo;
import com.seesea.seeseasms.util.CheckSumBuilder;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 上午 12:15
 * @Author xiechongyang
 */
@Service
public class NetEaseImpl extends AbstractChannel {

    //发送验证码的请求路径URL
    @Value("${url.send.netease}")
    private String send_url;
    @Value("${templateid.send}")
    private String template_id;

    @Value("${test.send}")
    private boolean test_send;
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
//    @Value("${app.key.netease}")
//    private String app_key;
//    @Value("${app.secret.netease}")
//    private String app_secret;


    @Override
    public ChannelResultBo send(SendSmsVo sendSmsVo, SmsChannel smsChannelPo) throws BizException {

        /**
         * 注：最重要的四个参数，务必参考计算示例服务器API，否则会出现{"desc":"bad http header","code":414}
         *
         * 参数	参数说明
         * AppKey	开发者平台分配的appkey
         * Nonce	随机数（最大长度128个字符）
         * CurTime	当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
         * CheckSum	SHA1(AppSecret + Nonce + CurTime),三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
         */
        Date reqData = null;
        Date rspData = null;
        SmsLog smsLogPo = new SmsLog();
        ChannelResultBo channelResultBo = new ChannelResultBo();
        try {
            //1 组织参数
            /**
             *  如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
             *  参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
             *  params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
             *         nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
             *         nvps.add(new BasicNameValuePair("mobile", MOBILE));
             *         nvps.add(new BasicNameValuePair("codeLen", CODELEN))
             */
            int vCode = getVCode();
            NetEaseSmsDto netEaseSmsDto = new NetEaseSmsDto();
            netEaseSmsDto.setCodeLen(Const.CODE_LEN);
            netEaseSmsDto.setAuthCode(vCode);
//            netEaseSmsDto.setDeviceId("");
            netEaseSmsDto.setMobile(sendSmsVo.getPhone());
//        netEaseSmsDto.setNeedUp();
            netEaseSmsDto.setTemplateid(Integer.valueOf(template_id));

            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(smsChannelPo.getSmsChannelPwd(), String.valueOf(vCode), curTime);
            List<Header> headers = new ArrayList<>();
            headers.add(new BasicHeader("AppKey", smsChannelPo.getSmsChannelKey()));
            headers.add(new BasicHeader("Nonce", String.valueOf(vCode)));
            headers.add(new BasicHeader("CurTime", curTime));
            headers.add(new BasicHeader("CheckSum", checkSum));
            headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));
            Map mapParams = MapUtil.BeanToMap(netEaseSmsDto);

            logInfo(sendSmsVo.getReqId(), "网易短信请求头", headers);
            logInfo(sendSmsVo.getReqId(), "网易短信请求参数", mapParams);

            byte[] bytes = null;
            //2 发起请求
            reqData = new Date();
            if (test_send) {
                String a = "{\"code\":200,\"msg\":\"88\",\"obj\":\"1908\"}";
                bytes = a.getBytes();
            } else {
                bytes = doPost(send_url, mapParams, headers);
            }
            rspData = new Date();

            //3 处理返回参数
            /**
             * {
             *   "code": 200,
             *   "msg": "88",
             *   "obj": "1908"
             * }
             */
            Assert.isNull(bytes, ResultCode.ER_1204);

            String strResult = new String(bytes);

            logInfo(sendSmsVo.getReqId(), "网易短信返回参数", strResult);

            Map<String, String> mapResult = JsonUtil.jsonToObj(strResult, Map.class);

            if (!(Const.SUCCESS.equals(String.valueOf(mapResult.get("code"))))) {
                Assert.isNullStr(mapResult.get("code"), ResultCode.ER_1205);
                throw new BizException(mapResult.get("code"), mapResult.get("msg"));
            }

            smsLogPo.setSmsReqLog(JsonUtil.objToJson(mapParams));
            smsLogPo.setSmsRspLog(strResult);
            channelResultBo.setvCode(String.valueOf(vCode));
            channelResultBo.setMsg(mapResult.get("msg"));
            channelResultBo.setSendDate(rspData);
        } catch (Exception e) {
            logError(sendSmsVo.getReqId(), "发送短信异常", e);
            if (e instanceof BizException) {
                BizException ex = (BizException) e;
                smsLogPo.setSmsReqLog("请求网易短信出错" + e.getMessage());
                smsLogPo.setSmsRspLog("请求网易短信出错");
                throw new BizException(ex.errCode, ex.getMessage());
            }
            //抛出未知异常
        } finally {
            //入库
            smsLogPo.setReqId(sendSmsVo.getReqId());
            smsLogPo.setReqTime(reqData);
            smsLogPo.setRspTime(rspData);
            smsLogPo.setSmsChannlId(smsChannelPo.getSmsChannelId());
            smsLogPo.setSmsId(sendSmsVo.getSmsId());
            updateSmsLog(smsLogPo);
        }

        //返回
        return channelResultBo;
    }

}
