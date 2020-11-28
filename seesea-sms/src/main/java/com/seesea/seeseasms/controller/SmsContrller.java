package com.seesea.seeseasms.controller;

import com.seesea.seeseacommon.base.Result;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseasms.base.BaseController;
import com.seesea.seeseasms.entity.vo.CheckSmsVo;
import com.seesea.seeseasms.entity.vo.SendSmsVo;
import com.seesea.seeseasms.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/9/15 下午 11:23
 * @Author xiechongyang
 */

@RestController
@RequestMapping(value = "v1")
public class SmsContrller extends BaseController {

    @Autowired
    private ISmsService iSmsService;

    //    @HystrixCommand(fallbackMethod = "SendSmsfallbackMethod")
    @PostMapping(value = "/sendvcode")
    public Object sendvcode(SendSmsVo sendSmsVo) throws BizException {

        Result result = sendSmsVo.getResult();
        Map map = iSmsService.send(sendSmsVo);
        result.setData(map);

        return result;

    }

    @PostMapping(value = "/checkvcode")
    public Object checkvcode(CheckSmsVo checkSmsVo) throws BizException {

        Result result = checkSmsVo.getResult();
        Map map = iSmsService.checkVCode(checkSmsVo);
        result.setData(map);

        return result;

    }


    public void loginfallbackMethod() {

    }


}
