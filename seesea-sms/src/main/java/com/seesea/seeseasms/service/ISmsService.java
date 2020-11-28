package com.seesea.seeseasms.service;


import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseasms.entity.vo.CheckSmsVo;
import com.seesea.seeseasms.entity.vo.SendSmsVo;

import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 下午 11:36
 * @Author xiechongyang
 */
public interface ISmsService {

    /**
     * 发送
     *
     * @param sendSmsVo
     * @return
     */
    Map<String, Object> send(SendSmsVo sendSmsVo) throws BizException;

    /**
     * 校验
     *
     * @param checkSmsVo
     * @return
     */
    Map<String, Object> checkVCode(CheckSmsVo checkSmsVo) throws BizException;
}
