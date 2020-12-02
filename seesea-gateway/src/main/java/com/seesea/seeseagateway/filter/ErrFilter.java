package com.seesea.seeseagateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.seesea.seeseacommon.common.ResultCode;
import com.seesea.seeseacommon.util.JsonUtil;
import com.seesea.seeseagateway.entity.GatewayLog;
import com.seesea.seeseagateway.entity.Req;
import com.seesea.seeseagateway.entity.Rsp;
import com.seesea.seeseagateway.mapper.GatewayLogMapper;
import com.seesea.seeseagateway.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/11/24 16:38
 */
@Component
public class ErrFilter extends ZuulFilter {

    @Autowired
    private GatewayLogMapper gatewayMapper;

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        Req req = PreFilter.thread.get();

        RequestContext ctx = RequestContext.getCurrentContext();

        Throwable throwable = ctx.getThrowable();
        LogUtil.logError(req.getReqId(),"网关异常处理器捕捉" , new Exception(throwable));

        Rsp rsp = new Rsp();
        rsp.setReqId(req.getReqId());
        rsp.setSequenceId(req.getSequenceId());
        rsp.setAccountId(req.getAccountId());
        rsp.setCode(ResultCode.ER_1010.code);
        rsp.setMsg(ResultCode.ER_1010.msg);
        String rspStr = "";
        try {
            rspStr = JsonUtil.objToJson(rsp);
        } catch (JsonProcessingException e) {
            LogUtil.logError(req.getReqId(),"异常处理器处理错误" , e);
        }finally {
            GatewayLog gatewayLog = new GatewayLog();
            gatewayLog.setErrCode(rsp.getCode());
            gatewayLog.setErrCode(rsp.getMsg());
            gatewayLog.setRspTime(new Date());
            gatewayLog.setReqId(req.getReqId());
            gatewayMapper.updateByPrimaryKey(gatewayLog);
            int a = 1/0;
        }

        ctx.setResponseBody(rspStr);
        return null;
    }
}
