package com.seesea.seeseagateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;
import com.seesea.seeseacommon.util.*;
import com.seesea.seeseagateway.entity.*;
import com.seesea.seeseagateway.mapper.AccountInfoMapper;
import com.seesea.seeseagateway.mapper.GatewayLogMapper;
import com.seesea.seeseagateway.mapper.ServiceInfoMapper;
import com.seesea.seeseagateway.service.ICheckService;
import com.seesea.seeseagateway.util.LogUtil;
import com.seesea.seeseagateway.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;


/**
 * @author xie
 * @description
 * @createTime 2020/11/23 19:15
 * @since JDK1.8
 */
@Component
public class PreFilter extends ZuulFilter {

    protected static ThreadLocal<Req> thread = new ThreadLocal<>();

    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;
    @Autowired
    private GatewayLogMapper gatewayMapper;

    /**
     * filterType代表过滤类型
     * PRE: 该类型的filters在Request routing到源web-service之前执行。用来实现Authentication、选择源服务地址等
     * ROUTING：该类型的filters用于把Request routing到源web-service，源web-service是实现业务逻辑的服务。这里使用HttpClient请求web-service。
     * POST：该类型的filters在ROUTING返回Response后执行。用来实现对Response结果进行修改，收集统计数据以及把Response传输会客户端。
     * ERROR：上面三个过程中任何一个出现错误都交由ERROR类型的filters进行处理。
     * 主要关注 pre、post和error。分别代表前置过滤，后置过滤和异常过滤。
     * 如果你的filter是pre的，就是指请求先进入pre的filter类，你可以进行一些权限认证，日志记录，或者额外给Request增加一些属性供后续的filter使用。pre会优先按照order从小到大执行，然后再去执行请求转发到业务服务。
     * 再说post，如果type为post，那么就会执行完被路由的业务服务后，再进入post的filter，在post的filter里，一般做一些日志记录，或者额外增加response属性什么的。
     * 最后error，如果在上面的任何一个地方出现了异常，就会进入到type为error的filter中。
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String reqId = IdGenerate.getId(IdGenerate.REQ_ID);

        Rsp rsp = new Rsp();
        rsp.setCode(ResultCode.SUCCESS.code);
        rsp.setMsg(ResultCode.SUCCESS.msg);
        rsp.setReqId(reqId);
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);

        Req req = null;

        try {
            /**
             * 获取参数
             */
            String reqStr = StreamUtils.copyToString(ctx.getRequest().getInputStream(), Charset.forName("UTF-8"));
            req = JsonUtil.jsonToObj(reqStr, Req.class);
            req.setReqId(reqId);
            LogUtil.logInfo(req.getReqId(), "网关请求日志", reqStr);
            thread.set(req);

            String sequenceId = req.getSequenceId();
            String accountId = req.getAccountId();
            String serviceId = req.getServiceId();
            String reqData = req.getData();
            String sign = req.getSign();

            /**
             * 校验参数
             */
            if (
                    StringUtil.isNull(sequenceId) ||
                            StringUtil.isNull(sign) ||
                            StringUtil.isNull(accountId) ||
                            StringUtil.isNull(serviceId) ||
                            StringUtil.isNull(reqData)) {
                throw new BizException(ResultCode.ER_1003);
            }

            AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey(accountId);
            String MD5Key = accountInfo.getAccountKey();
            String AESKey = accountInfo.getAccountSecret();
            /**
             * 验签
             */
            String sortStr = StringUtil.sortValue(req);
            String signStr = MD5Util.getMd5(sortStr + "&KEY=" + MD5Key);
            if (!sign.equals(signStr)) {
//                rsp.setCode(ResultCode.ER_1001.code);
//                rsp.setMsg(ResultCode.ER_1001.msg);
//                ctx.setResponseBody(JsonUtil.objToJson(rsp));
//                return null;
                throw  new BizException(ResultCode.ER_1001);
            }


            /***
             * 解密参数
             */
            try{
                String dataStr = AESUtil.decrypt(req.getData(), AESKey);
//             dataStr = JsonUtil.jsonAddParam(dataStr, "reqId", reqParam.getReqId());
                req.setData(dataStr);
            }catch (Exception e){
                LogUtil.logError(reqId,"网关错误",e);
                throw new BizException(ResultCode.ER_1007);
            }



            /**
             * 服务鉴权
             */
            ServiceInfo serviceInfo = serviceInfoMapper.selectByPrimaryKey(serviceId);
            if (serviceInfo == null) {
//                rsp.setCode(ResultCode.ER_1005.code);
//                rsp.setMsg(ResultCode.ER_1005.msg);
//                ctx.setResponseBody(JsonUtil.objToJson(rsp));
//                return null;
                throw new BizException(ResultCode.ER_1005);
            }
            if (serviceInfo.getIsCheck()) {
                ICheckService iCheckService = SpringContextUtil.getBean("Check" + serviceId + "Impl", ICheckService.class);
                if (iCheckService.check(req)) {
//                    rsp.setCode(ResultCode.ER_1002.code);
//                    rsp.setMsg(ResultCode.ER_1002.msg);
//                    ctx.setResponseBody(JsonUtil.objToJson(rsp));
//                    return null;
                    throw new BizException(ResultCode.ER_1002);
                }
            }
            String forwardStr = req.getData();
            JsonUtil.jsonAddParam(forwardStr,"reqId",reqId);
            JsonUtil.jsonAddParam(forwardStr,"sequenceId",sequenceId);
            JsonUtil.jsonAddParam(forwardStr,"accountId",accountId);
            /**
             * 转发
             */
            final byte[] reqByte = forwardStr.getBytes();
            ctx.setSendZuulResponse(true);
            ctx.setRequest(new HttpServletRequestWrapper(request) {
                               /**
                                * 重写请求参数
                                * @return
                                * @throws IOException
                                */
                               @Override
                               public ServletInputStream getInputStream() {
                                   return new ServletInputStreamWrapper(reqByte);
                               }

                               @Override
                               public int getContentLength() {
                                   return reqByte.length;
                               }

                               @Override
                               public long getContentLengthLong() {
                                   return reqByte.length;
                               }

                               /**
                                * 重新uri
                                * @return
                                */
                               @Override
                               public String getRequestURI() {
                                   return serviceInfo.getServiceUrl();
                               }
                           }
            );

        } catch (Exception e) {
            LogUtil.logError(reqId,"网关处理错误",e);
            throw new  ZuulException(e, 1,"");
        } finally {
            GatewayLog gatewayLog = new GatewayLog();
            gatewayLog.setReqId(reqId);
            gatewayLog.setOrderId(req.getSequenceId());
            gatewayLog.setAccoutId(req.getAccountId());
//                gatewayLog.setAppId(appId);
            gatewayLog.setServiceId(req.getServiceId());
            gatewayLog.setAccountReqTime(new Date());
            gatewayLog.setReqTime(new Date());
            gatewayLog.setRspTime(new Date());
//            if (!ResultCode.ER_1000.equals(rsp.getCode())) {
//                gatewayLog.setErrCode(errCode);
//                gatewayLog.setErrMsg(errMsg);
//            }
            gatewayMapper.insert(gatewayLog);
        }

        return null;
    }
}
//todo
// 1 远程获取 账户信息
// 2 使用err 过滤器