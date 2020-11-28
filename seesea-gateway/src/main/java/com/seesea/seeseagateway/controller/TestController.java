package com.seesea.seeseagateway.controller;

import com.seesea.redis.RedisConfiguration;
import com.seesea.redis.RedisUtils;
import com.seesea.seeseagateway.entity.GatewayLog;
import com.seesea.seeseagateway.entity.ServiceInfo;
import com.seesea.seeseagateway.mapper.GatewayLogMapper;
import com.seesea.seeseagateway.mapper.ServiceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/11/17 15:30
 */
@RefreshScope
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Value("${test}")
    private String a;

    @GetMapping(value = "a")
    public String getA(){
        return a;
    }

    @Autowired
    private GatewayLogMapper gatewayLogMapper;
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;


//
//    @Autowired
//    private RedisConfiguration configuration;

    @GetMapping(value = "r")
    public String getRedis(){

       System.out.println(RedisConfiguration.getHost());
//       System.out.println(configuration);

//        return ;
        RedisUtils.hset("1","key","aa");

        List<ServiceInfo> s = serviceInfoMapper.selectAll();
        System.out.println(s);

        return RedisUtils.hget("1","key");
    }

}
