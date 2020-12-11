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

import javax.servlet.http.HttpServletResponse;
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
        Rsp rsp = new Rsp();
        rsp.setReqId(req.getReqId());
        rsp.setSequenceId(req.getSequenceId());
        rsp.setAccountId(req.getAccountId());
        try {
            if (isSend) {
                if (ctx.getResponseStatusCode() >= 200 && ctx.getResponseStatusCode() < 300) {
                    rspStr = StreamUtils.copyToString(ctx.getResponseDataStream(), Charset.forName("UTF-8"));
                    LogUtil.logInfo(req.getReqId(), "网关返回参数", rspStr);
                    /**
                     * 返回信息入库保存
                     */
                    Map map = JsonUtil.jsonToObj(rspStr, Map.class);
                    gatewayLog.setErrCode(map.get("code").toString());
                    gatewayLog.setErrMsg(map.get("msg").toString());

                }else {
                    gatewayLog.setErrCode(ResultCode.ER_1008.code);
                    gatewayLog.setErrMsg(ResultCode.ER_1008.msg);
                    rsp.setCode(ResultCode.ER_1008.code);
                    rsp.setMsg(ResultCode.ER_1008.msg);
                    rspStr =  JsonUtil.objToJson(rsp);
                }
            }else {
                String rspBody = ctx.getResponseBody();
                Map map = JsonUtil.jsonToObj(rspBody, Map.class);
                gatewayLog.setErrCode(map.get("code").toString());
                gatewayLog.setErrMsg(map.get("msg").toString());
                rsp.setCode(map.get("code").toString());
                rsp.setMsg(map.get("msg").toString());
                rspStr = rspBody;
            }
        } catch (Exception e) {
            LogUtil.logError(req.getReqId(), "网关返回参数处理错误", e);
//            throw new ZuulException(e, 1,"");
            rspStr = "{\"code\":\"1010\",\"msg\":\"网关未知异常\",\"reqId\":\"10202012072018406000\",\"sequenceId\":null,\"accountId\":null,\"data\":null,\"rspTime\":1607343520073}\n";
        }finally {
            gatewayLog.setReqId(req.getReqId());
            gatewayLog.setSequenceId(req.getSequenceId());
            gatewayLog.setRspTime(new Date());
            gatewayLog.setReqId(req.getReqId());
            gatewayMapper.updateByPrimaryKeySelective(gatewayLog);
        }

        ctx.setResponseBody(rspStr);

        String k =  ctx.getResponseBody();
        HttpServletResponse response =  ctx.getResponse();
        return null;
    }
}
