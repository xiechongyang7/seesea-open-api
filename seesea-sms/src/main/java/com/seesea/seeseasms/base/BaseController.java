package com.seesea.seeseasms.base;

import com.seesea.seeseacommon.base.BaseLogger;
import com.seesea.seeseacommon.base.Result;
import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Description 所有controller都要继承此类
 * @Since JDK1.8
 * @Createtime 2018/9/15 下午 11:45
 * @Author xiechongyang
 */
public class BaseController extends BaseLogger implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exp(HttpServletRequest request, Exception ex) {


        Result result = new Result();
        String reqId = "";
        String httpMethod = request.getMethod();
//        if(!"post".equalsIgnoreCase(httpMethod)){
//            return "err";
//        }
        try {
            Map map = request.getParameterMap();
            if ("post".equalsIgnoreCase(httpMethod)) {
                reqId = map.get("reqId").toString();
            }
            result.setReqId(reqId);
//            result.setData(null);

        } catch (Exception e) {
            logError(reqId, "BaseController获取参数异常", e);
        }

        if (ex instanceof NullPointerException) {
            logError(reqId, "空指针异常", ex);
            result.setCode(ResultCode.ER_9000.code);
            result.setMsg(ResultCode.ER_9000.msg);
        } else if (ex instanceof SQLException) {
            logError(reqId, "sql错误", ex);
            result.setCode(ResultCode.ER_9002.code);
            result.setMsg(ResultCode.ER_9002.msg);
        } else if (ex instanceof BizException) {
            BizException e = (BizException) ex;
//            logError(reqId, "业务处理错误", ex);
            result.setCode(e.errCode);
            result.setMsg(e.getMessage());
        } else {
            logError(reqId, "未捕捉异常", ex);
            result.setCode(ResultCode.ER_9001.code);
            result.setMsg(ResultCode.ER_9001.msg);
        }
        return result;
    }
}
