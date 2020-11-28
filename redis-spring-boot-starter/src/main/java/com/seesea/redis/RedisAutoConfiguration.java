package com.seesea.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
* @description
* @since JDK1.8
* @createTime 2020/11/17 20:57
* @author xie
*/
@Configuration//@Configuration：表明此类是一个配置类，将变为一个bean被spring进行管理。
@EnableConfigurationProperties(RedisProperties.class)//@EnableConfigurationProperties：启用属性配置，将读取HelloServiceProperties里面的属性。
//@ConditionalOnClass(RedisConfiguration.class)//@ConditionalOnClass：当类路径下面有RedisConfiguration此类时，自动配置。
public class RedisAutoConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);


    @Autowired
    private RedisProperties redisProperties;


    @Bean
//    @ConditionalOnMissingBean(RedisConfiguration.class)//@ConditionalOnMissingBean：当容器中没有指定bean是，创建此bean。
    @ConditionalOnProperty(prefix = "my.redis", value = "enabled", matchIfMissing = true)//@ConditionalOnProperty：判断指定的属性是否具备指定的值。
    public void RedisConfigurationInit() {

        logger.info("加载redis配置");
        logger.info(redisProperties.toString());
        logger.info("加载redis配置");

        RedisConfiguration.setMode(redisProperties.getMode());
        RedisConfiguration.setHost(redisProperties.getHost());
        RedisConfiguration.setPort(redisProperties.getPort());
        RedisConfiguration.setMaxTotal(redisProperties.getMaxTotal());
        RedisConfiguration.setMaxIdle(redisProperties.getMaxIdle());
        RedisConfiguration.setMinIdle(redisProperties.getMinIdle());
        RedisConfiguration.setMaxWait(redisProperties.getMaxWait());
        RedisConfiguration.setTimeout(redisProperties.getTimeout());
        RedisConfiguration.setNodes(redisProperties.getNodes());
        RedisConfiguration.setName(redisProperties.getName());
        RedisConfiguration.setPassword(redisProperties.getPassword());

    }

}
