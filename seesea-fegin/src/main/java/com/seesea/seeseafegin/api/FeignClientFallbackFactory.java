package com.seesea.seeseafegin.api;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/28 15:51
 */
@Component
public class FeignClientFallbackFactory implements FallbackFactory<Object> {
    private final Logger logger = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    public Boolean create(Throwable throwable) {
        logger.info("熔断方法执行 原因", throwable);
//        Map map = new HashMap();
//        map.put("code", "9999");
//        map.put("msg", "调用服务错误");
        return false;
    }

}
