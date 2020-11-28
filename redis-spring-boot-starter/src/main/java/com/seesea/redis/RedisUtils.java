package com.seesea.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/24 下午 9:01
 * @Author xiechongyang
 */
public class RedisUtils {

    private static Integer EXPIRETIME = 60000;

    /**
     * 插入string值
     *
     * @param key
     * @param expire
     * @param value
     * @return
     */
    public static boolean setString(String key, Integer expire, String value) {
        Jedis jedis = JedisPoolInit.getJedis();
        boolean result = true;
        try {
            jedis.setex(key, expire, value);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
        return result;
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @return
     */
    public static boolean expire(String key, int expire) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            long i = jedis.expire(key, expire);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * PEXPIRE
     * 是否存在key
     *
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * PEXPIRE
     * 是否存在key
     *
     * @param key
     * @return
     */
    public static boolean del(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            long i = jedis.del(key);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }
    /*********************************************hx*********************************************/
    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * <p>
     * 若域 field 已经存在，该操作无效。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static boolean hsetnx(String key, String field, String value) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            long i = jedis.hsetnx(key, field, value);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param field
     * @return
     */
    public static boolean hdel(String key, String field) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            long i = jedis.hdel(key, field);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 返回哈希表 key 中的所有域。
     *
     * @param key
     * @return
     */
    public static Set<String> hkeys(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     *
     * @param key
     * @return
     */
    public static boolean hexists(String key, String field) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hexists(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 获取指定的值
     *
     * @param key
     * @param field
     * @return
     */
    public static String hget(String key, String field) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }


    /**
     * 返回哈希表 key 中，所有的域和值。
     *
     * @param key
     * @return
     */
    public static Map<String, String> hgetall(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     *
     * @param key
     * @param field
     * @return
     */
    public static Long hincrby(String key, String field, int value) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }


    /**
     * @param key
     * @return
     */
    public static Long hlen(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hlen(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。
     *
     * @param key
     * @param fields
     * @return
     */
    public static List<String> hmget(String key, String[] fields) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     * <p>
     * 此命令会覆盖哈希表中已存在的域
     *
     * @param key
     * @param fields
     * @return
     */
    public static String hmset(String key, Map<String, String> fields) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hmset(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * <p>
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * <p>
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param fields
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    public static Long hset(String key, String fields, String value) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolInit.getJedis();
            return jedis.hset(key, fields, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }


    /**
     * 返回哈希表 key 中所有域的值。
     *
     * @param key
     * @return
     */
    public static List<String> hvals(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }


    /**
     * 返回哈希表 key 中， 与给定域 field 相关联的值的字符串长度（string length）。
     * <p>
     * 如果给定的键或者域不存在， 那么命令返回 0 。
     *
     * @param key
     * @return
     */
    public static Long hstrlen(String key) {
        Jedis jedis = JedisPoolInit.getJedis();
        try {
            return jedis.strlen(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JedisPoolInit.closeJedis(jedis);
        }
    }
    /*********************************************hx*********************************************/
}
