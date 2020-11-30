package com.seesea.seeseagateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(basePackages = "com.seesea.seeseagateway.mapper")
@EnableZuulProxy
public class SeeseaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeeseaGatewayApplication.class, args);
    }

}
