package com.seesea.redis;


/**
 * @author xie
 * @description
 * @createTime 2020/11/17 20:56
 * @since JDK1.8
 */
public class RedisConfiguration {




    /**
     * 连接模式
     */
    private static String mode;
    /**
     * 主机
     */
    private static String host;
    /**
     * 端口
     */
    private static String port;
    /**
     * 连接池最大数量
     */
    private static Integer maxTotal;
    /**
     * 最大空闲连接数量
     */
    private static Integer maxIdle;
    /**
     * 连接池最小空闲连接数量
     */
    private static Integer minIdle;
    /**
     * 获取连接池连接最长等待时间
     */
    private static Integer maxWait;
    /**
     * 读写超时时间设置
     */
    private static Integer timeout;
    /**
     * redis节点
     */
    private static String nodes;
    /**
     * redis名称
     */
    private static String name;
    /**
     * redis密码
     */
    private static String password;

    public static String getMode() {
        return mode;
    }

    public static void setMode(String mode) {
        RedisConfiguration.mode = mode;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        RedisConfiguration.host = host;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        RedisConfiguration.port = port;
    }

    public static Integer getMaxTotal() {
        return maxTotal;
    }

    public static void setMaxTotal(Integer maxTotal) {
        RedisConfiguration.maxTotal = maxTotal;
    }

    public static Integer getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(Integer maxIdle) {
        RedisConfiguration.maxIdle = maxIdle;
    }

    public static Integer getMinIdle() {
        return minIdle;
    }

    public static void setMinIdle(Integer minIdle) {
        RedisConfiguration.minIdle = minIdle;
    }

    public static Integer getMaxWait() {
        return maxWait;
    }

    public static void setMaxWait(Integer maxWait) {
        RedisConfiguration.maxWait = maxWait;
    }

    public static Integer getTimeout() {
        return timeout;
    }

    public static void setTimeout(Integer timeout) {
        RedisConfiguration.timeout = timeout;
    }

    public static String getNodes() {
        return nodes;
    }

    public static void setNodes(String nodes) {
        RedisConfiguration.nodes = nodes;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        RedisConfiguration.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        RedisConfiguration.password = password;
    }

    public static String info() {
        return "RedisConfiguration{" +
                "mode='" + mode + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", maxWait=" + maxWait +
                ", timeout=" + timeout +
                ", nodes='" + nodes + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
