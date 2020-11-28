package com.seesea.seeseagateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.seesea.seeseacommon.base.exception.BizException;
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
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;


/**
 * @Description
 * @Since JDK1.8
 * @Createtime 18:04 2018/9/13
 * @Author 谢重阳
 */
@Component
public class PostFilter extends ZuulFilter {

    @Autowired
    private GatewayLogMapper gatewayMapper;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
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
        boolean isSend = ctx.sendZuulResponse();

        GatewayLog gatewayLog = new GatewayLog();

        String rspStr = null;

        try {
            if (isSend) {
                if (ctx.getResponseStatusCode() >= 200 && ctx.getResponseStatusCode() < 300) {
                    rspStr = StreamUtils.copyToString(ctx.getResponseDataStream(), Charset.forName("UTF-8"));
                    LogUtil.logInfo(req.getReqId(), "网关返回参数", rspStr);

                    /**
                     * 返回信息入库保存
                     */
                    Map map = JsonUtil.jsonToObj(rspStr, Map.class);
                    Rsp rspParam = new Rsp();
                    rspParam.setCode(map.get("code").toString());
                    rspParam.setMsg(map.get("msg").toString());
                    rspParam.setData(rspStr);
                    rspParam.setRspTime(new Date());
                    rspParam.setReqId(req.getReqId());


                    gatewayLog.setErrCode(map.get("code").toString());
                    gatewayLog.setErrMsg(map.get("msg").toString());


                    ctx.setResponseBody(rspStr);
                }else {
                    gatewayLog.setErrCode(ResultCode.ER_1008.code);
                    gatewayLog.setErrMsg(ResultCode.ER_1008.msg);
                    throw new BizException(ResultCode.ER_1008);
                }
            }else {
                gatewayLog.setErrCode(ResultCode.ER_1009.code);
                gatewayLog.setErrMsg(ResultCode.ER_1009.msg);
                throw new BizException(ResultCode.ER_1009);
            }
        } catch (Exception e) {
            LogUtil.logError(req.getReqId(), "网关返回参数处理错误", e);
            throw new ZuulException(e, 1,"");
        }finally {
            gatewayLog.setRspTime(new Date());
            gatewayLog.setReqId(req.getReqId());
            gatewayMapper.updateByPrimaryKey(gatewayLog);

        }
        return null;
    }
}
