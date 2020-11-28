package com.seesea.redis;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xie
 * @description 读取属性
 * @createTime 2020/11/17 20:50
 * @since JDK1.8
 */
@ConfigurationProperties(prefix = "my.redis")
public class RedisProperties {

    /**
     * 连接模式
     */
    private String mode = "";
    /**
     * 主机
     */
    private String host = "127.0.0.1";
    /**
     * 端口
     */
    private String port = "6379";
    /**
     * 连接池最大数量
     */
    private Integer maxTotal = 100;
    /**
     * 最大空闲连接数量
     */
    private Integer maxIdle = 5;
    /**
     * 连接池最小空闲连接数量
     */
    private Integer minIdle = 2;
    /**
     * 获取连接池连接最长等待时间
     */
    private Integer maxWait = 60;
    /**
     * 读写超时时间设置
     */
    private Integer timeout = 60;
    /**
     * redis节点
     */
    private String nodes;
    /**
     * redis名称
     */
    private String name;
    /**
     * redis密码
     */
    private String password;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RedisProperties{" +
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