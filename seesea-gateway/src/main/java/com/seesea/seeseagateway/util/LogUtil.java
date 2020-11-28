package com.seesea.seeseagateway.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
* @description
* @since JDK1.8
* @createTime 2020/11/23 19:32
* @author xie
*/
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * infor日志
     *
     * @param reqId  []
     * @param direct [描述]
     * @param logStr []
     */
    public static void logInfo(String reqId, String direct, String logStr) {
        MDC.put("REQID", reqId);// 日志分表
        logger.info(String.format("[%s] [%s] [%s]", reqId, direct, logStr));
        MDC.remove("REQID");
    }

    /**
     * error日志
     *
     * @param reqId
     * @param direct
     * @param logStr
     */
    public static void logError(String reqId, String direct, Exception logStr) {
        MDC.put("REQID", reqId);// 日志分表
        logger.error(String.format("[%s] [%s]", reqId, direct), logStr);
        MDC.remove("REQID");
    }
}
