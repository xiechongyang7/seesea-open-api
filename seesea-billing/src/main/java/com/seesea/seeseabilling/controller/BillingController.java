package com.seesea.seeseabilling.controller;

//import com.seesea.seeseabilling.base.BaseController;
import com.seesea.seeseabilling.entity.BillingVo;
import com.seesea.seeseacommon.base.BaseLogger;
import com.seesea.seeseacommon.base.exception.BizException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/14 16:43
 */
@RestController
@RequestMapping(value = "v1")
public class BillingController extends BaseLogger {

    //    @HystrixCommand(fallbackMethod = "SendSmsfallbackMethod")
    @ResponseBody
    @PostMapping(value = "/billing")
    public Boolean billing(BillingVo vo) throws BizException {

        logger.info(vo.toString());

        return true;

    }

}
