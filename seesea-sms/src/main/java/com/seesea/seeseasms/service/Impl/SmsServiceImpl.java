package com.seesea.seeseasms.service.Impl;


import com.seesea.redis.RedisUtils;
import com.seesea.seeseacommon.base.BaseService;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;
import com.seesea.seeseacommon.constant.Const;
import com.seesea.seeseacommon.constant.RedisKey;
import com.seesea.seeseacommon.util.spring.SpringContextUtil;
import com.seesea.seeseacommon.util.IdGenerate;
import com.seesea.seeseacommon.util.StringUtil;
import com.seesea.seeseasms.entity.SmsChannel;
import com.seesea.seeseasms.entity.SmsSendCheck;
import com.seesea.seeseasms.entity.bo.ChannelResultBo;
import com.seesea.seeseasms.entity.vo.CheckSmsVo;
import com.seesea.seeseasms.entity.vo.SendSmsVo;
import com.seesea.seeseasms.mapper.SmsChannelMapper;
import com.seesea.seeseasms.mapper.SmsSendCheckMapper;
import com.seesea.seeseasms.service.ISmsChannel;
import com.seesea.seeseasms.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 下午 11:38
 * @Author xiechongyang
 */
@Service
public class SmsServiceImpl extends BaseService implements ISmsService {


    @Autowired
    private SmsChannelMapper smsChannelPoMapper;
    @Autowired
    private SmsSendCheckMapper smsSendAndCheckPoMapper;

    @Value("${limit.check.num}")
    private int limit_check_num;

    @Value("${limit.send.num}")
    private int limit_send_num;

    @Value("${expire.time}")
    private int expire_time;

    @Value("${limit.send.time}")
    private int limit_send_time;

    @Value("${test.send}")
    private boolean test_send;

    @Override
    public Map<String, Object> send(SendSmsVo sendSmsVo) throws BizException {

        String phone = sendSmsVo.getPhone();

        ChannelResultBo channelResult = null;
        SmsSendCheck smsSendAndCheckPo = new SmsSendCheck();
        //设置短信id
        sendSmsVo.setSmsId(IdGenerate.getId(IdGenerate.SMS_ID));
        SmsChannel smsChannelPo = null;
        String num = null;
        try {
            //1 判断是否发送

            //1.1 一天发送了多少次
            num = RedisUtils.hget(String.format(RedisKey.PHONE_SEND_NUM, phone), RedisKey.PHONE_SEND_NUM_FILED);
            if (!StringUtil.isNull(num)) {
                if (Integer.valueOf(num) == limit_send_num) {
                    throw new BizException(ResultCode.ER_1206,  limit_send_num+ "用默认的吧1234");
                }
            }

            //1.2 1分钟是否发送
            if (RedisUtils.exists(String.format(RedisKey.PHONE_SEND_SMS, phone))) {
                logInfo(sendSmsVo.getReqId(), phone, "已经发送");
                throw new BizException(ResultCode.ER_1201);
            }


            //2 找个能发的
            List<SmsChannel> smsChannelPos = smsChannelPoMapper.selectACanUseChannel();
            if (smsChannelPos.size() == 0) {
                logInfo(sendSmsVo.getReqId(), phone, "没有能发的通道了");
                throw new BizException(ResultCode.ER_1202);
            }
            /**
             * 找个有免费短信的
             */
            smsChannelPo = smsChannelPos.get(0);
            ISmsChannel smsChannel = SpringContextUtil.getBean(smsChannelPo.getSmsChannelName() + "Impl", ISmsChannel.class);

            logInfo(sendSmsVo.getReqId(), phone, "使用" + smsChannelPos.get(0).getSmsChannelName() + "发送");

            //3 发送

            channelResult = smsChannel.send(sendSmsVo, smsChannelPo);

            smsSendAndCheckPo.setSmsSendCode(channelResult.getvCode());
            smsSendAndCheckPo.setSmsSendMsg(channelResult.getMsg());
            smsSendAndCheckPo.setSmsSendStatus(Const.SUCCESS_SEND);
            smsSendAndCheckPo.setSmsSendTime(channelResult.getSendDate());
            smsSendAndCheckPo.setStatus(Const.ALREADY_SEND);
            smsSendAndCheckPo.setRemark("已经发送");


            //4.1改redis
            //4.1.1加入redis
            Map<String, String> fieldMap = new HashMap<>();
            //验证码
            fieldMap.put(RedisKey.VCODE_FILED, channelResult.getvCode());
            //校验次数
            fieldMap.put(RedisKey.VCODE_CHECK_FILED, "0");
            //发送ID
            fieldMap.put(RedisKey.SEND_ID_FILED, sendSmsVo.getSmsId());
            RedisUtils.hmset(String.format(RedisKey.PHONE_SEND_SMS, phone), fieldMap);
            //设置超时时间
            RedisUtils.expire(String.format(RedisKey.PHONE_SEND_SMS, phone), expire_time);

            //4.1.2限制一天发送次数
            if (!StringUtil.isNull(num)) {
                RedisUtils.hincrby(String.format(RedisKey.PHONE_SEND_NUM, phone), RedisKey.PHONE_SEND_NUM_FILED, 1);
            } else {
                RedisUtils.hset(String.format(RedisKey.PHONE_SEND_NUM, phone), RedisKey.PHONE_SEND_NUM_FILED, "1");
                /**
                 * 限制一天
                 */
                RedisUtils.expire(String.format(RedisKey.PHONE_SEND_NUM, phone), limit_send_time);
            }


            //修改剩余条数
            if (!test_send) {
                smsChannelPoMapper.updateChannlRemainder();
            }
            Map<String, Object> result = new HashMap<>();
//            result.put("code", "1555");
            result.put("msg", channelResult.getMsg());
            //5 返回
            //发送完成
            logInfo(sendSmsVo.getReqId(), "发送完成" + phone, "验证码" + channelResult.getvCode());
            return result;
        } catch (BizException e) {

            //加入异常信息
            logError(sendSmsVo.getReqId(), "短信服务发送错误", e);

            smsSendAndCheckPo.setSmsSendMsg(e.getMessage());
            smsSendAndCheckPo.setSmsSendStatus(Const.SEND_FAIL);
            smsSendAndCheckPo.setStatus(Const.FAIL_SEND);
            smsSendAndCheckPo.setRemark("发送失败");
//
//            result.put("code", e.errCode);
//            result.put("msg", e.getMessage());

            throw e;
        } finally {

            //5改数据库
            if (smsChannelPo != null) {
                smsSendAndCheckPo.setSmsChannel(smsChannelPo.getSmsChannelId());
            }
            smsSendAndCheckPo.setReqId(sendSmsVo.getReqId());
            smsSendAndCheckPo.setPhone(sendSmsVo.getPhone());
            smsSendAndCheckPo.setSmsId(sendSmsVo.getSmsId());
            smsSendAndCheckPo.setSmsWork(sendSmsVo.getWorkType());
            smsSendAndCheckPo.setUserId("");
            smsSendAndCheckPoMapper.insert(smsSendAndCheckPo);
        }

    }

    @Override
    public Map<String, Object> checkVCode(CheckSmsVo checkSmsVo) throws BizException {


        String phone = checkSmsVo.getPhone();
        String checkVode = checkSmsVo.getVcode();
        String smsId = null;
        String sendCode = null;
        String checkNum = null;
        SmsSendCheck smsSendAndCheckPo = new SmsSendCheck();
        try {
            logInfo(checkSmsVo.getReqId(), "校验验证", "手机号" + phone + "验证码" + checkVode);
            //1 校验是否发送
//            if(!RedisUtils.exists(String.format(RedisKey.PHONE_SEND_SMS,phone))){
//                throw new BizException(ResultCode.ER_1207,ResultCode.ER_1207_MSG);
//            }
            //直接获取减少对redis的操作 防止在判断是否有和取值之间 key过期
            Map<String, String> fieldMap = RedisUtils.hgetall(String.format(RedisKey.PHONE_SEND_SMS, phone));
            if (fieldMap.isEmpty()) {
                boolean isSendTaday = RedisUtils.exists(String.format(RedisKey.PHONE_SEND_NUM, phone));
                if (isSendTaday) {
                    throw new BizException(ResultCode.ER_1209);
                }
                throw new BizException(ResultCode.ER_1207);
            }
            sendCode = fieldMap.get(RedisKey.VCODE_FILED);
            checkNum = fieldMap.get(RedisKey.VCODE_CHECK_FILED);
            smsId = fieldMap.get(RedisKey.SEND_ID_FILED);
            logInfo(checkSmsVo.getReqId(), "校验验证redis的信息", "\nsendCode" + sendCode + "\ncheckNum" + checkNum + "\nsmsId" + smsId);
            //2 校验是否是登录验证码
            if (!sendCode.equals(checkVode)) {
//                检查校验次数 防止暴力破解
                if (Integer.valueOf(checkNum) >= limit_check_num) {
                    RedisUtils.del(String.format(RedisKey.PHONE_SEND_SMS, phone));
                    throw new BizException(ResultCode.ER_1208, "3");
                } else {
                    RedisUtils.hincrby(String.format(RedisKey.PHONE_SEND_SMS, phone), RedisKey.VCODE_CHECK_FILED, 1);
                }
                throw new BizException(ResultCode.ER_1210);
            }
            //3 成功就即时删除redis的信息
            RedisUtils.del(String.format(RedisKey.PHONE_SEND_SMS, phone));

            smsSendAndCheckPo.setSmsCheckMsg(Const.CHECK_MSG);
            smsSendAndCheckPo.setSmsCheckStatus(Const.SUCCESS_CHECK);

            Map<String, Object> result = new HashMap<>();

            result.put("code", ResultCode.SUCCESS);
            result.put("msg", ResultCode.SUCCESS.msg);

            return result;
        } catch (BizException e) {
            logError(checkSmsVo.getReqId(), "校验验证码不对", e);
//            result.put("code", e.errCode);
//            result.put("msg", e.getMessage());

            smsSendAndCheckPo.setSmsCheckMsg(e.getMessage());
            smsSendAndCheckPo.setSmsCheckStatus(e.errCode);

            throw e;
        } finally {
            //4 校验错误的情况也入库
            if (!StringUtil.isNull(smsId)) {
                smsSendAndCheckPo.setSmsId(smsId);
                smsSendAndCheckPo.setSmsCheckCode(sendCode);
                smsSendAndCheckPo.setSmsCheckTime(new Date());
                smsSendAndCheckPo.setStatus(Const.COMPLETE);
                smsSendAndCheckPo.setRemark("校验成功");
            }
            smsSendAndCheckPoMapper.updateByPrimaryKey(smsSendAndCheckPo);
        }

    }
}
