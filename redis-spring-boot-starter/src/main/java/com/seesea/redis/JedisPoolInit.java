package com.seesea.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.util.Pool;


/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/24 下午 9:00
 * @Author xiechongyang
 */
public class JedisPoolInit {

    private static final Logger logger = LoggerFactory.getLogger(JedisPoolInit.class);


    /**
     * 私有化构造器
     */
    private JedisPoolInit() {

    }

    private static Pool<Jedis> POOL;

    static {
        try{
            logger.info("----初始化REDIS连接池----");
            logger.info("----REDIS配置----");
            logger.info(RedisConfiguration.info());
            //连接池配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisConfiguration.getMaxTotal());
            config.setMaxIdle(RedisConfiguration.getMaxIdle());
            config.setMinIdle(RedisConfiguration.getMinIdle());
            config.setMaxWaitMillis(RedisConfiguration.getMaxWait());
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            POOL = new JedisPool(config, RedisConfiguration.getHost(), Integer.valueOf(RedisConfiguration.getPort()), RedisConfiguration.getTimeout(), RedisConfiguration.getPassword());
            logger.info("----初始化REDIS连接池结果---成功----");
        }catch (Exception e){
            logger.error("----初始化REDIS连接池结果---失败----",e);
        }

    }

    /**
     * 获取实例
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 销毁对象
            if (jedis != null) {
                jedis.close();
            }
        }
        return jedis;
    }

    /**
     * 销毁实例
     *
     * @param jedis
     */
    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
