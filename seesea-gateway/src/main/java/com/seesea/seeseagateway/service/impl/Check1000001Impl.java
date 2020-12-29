package com.seesea.seeseagateway.service.impl;

import com.seesea.seeseacommon.base.BaseLogger;
import com.seesea.seeseafegin.api.BillingApi;
import com.seesea.seeseafegin.entity.BillingVo;
import com.seesea.seeseagateway.entity.Req;
import com.seesea.seeseagateway.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/17 15:55
 */
@Service(value = "Check1000001Impl")
public class Check1000001Impl extends BaseLogger implements ICheckService  {

    @Autowired
    private BillingApi api;

    @Override
    public boolean check(Req req) {
        boolean a = false;
        try {
            BillingVo vo = new BillingVo();

            vo.setAccountId(req.getAccountId());
            vo.setReqId(req.getReqId());

            a = api.billing(vo);
            logger.info("校验"  +  (a?"成功":"失败"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }
}
