package com.seesea.seeseacommon.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @Description 所有需要日志的都应继承此类 并且以reqid作为跟踪线索
 * @Since JDK1.8
 * @Createtime 2018/9/15 下午 11:24
 * @Author xiechongyang
 */
public class BaseLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * infor日志
     *
     * @param reqId  []
     * @param direct [描述]
     * @param logStr []
     */
    public void logInfo(String reqId, String direct, Object logStr) {
        MDC.put("REQID", reqId);// 日志分表
        logger.info(String.format("[%s] [%s] [%s]", reqId, direct, logStr));
    }

    /**
     * error日志
     *
     * @param reqId  请求id
     * @param direct 指明异常
     * @param logStr 异常信息
     */
    public void logError(String reqId, String direct, Exception logStr) {
        MDC.put("REQID", reqId);// 日志分表
        logger.error(String.format("[%s] [%s]", reqId, direct), logStr);
    }
}
