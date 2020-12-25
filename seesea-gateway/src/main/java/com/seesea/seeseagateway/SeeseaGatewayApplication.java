package com.seesea.seeseagateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.seesea.seeseagateway.mapper")
@EnableZuulProxy
@EnableFeignClients(basePackages = "com.seesea.seeseafegin.api")
public class SeeseaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeeseaGatewayApplication.class, args);
    }

}
