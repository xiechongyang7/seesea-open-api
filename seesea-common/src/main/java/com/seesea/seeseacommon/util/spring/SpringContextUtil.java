package com.seesea.seeseacommon.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @Description spring ioc工具类 如果想使用此类应在@Configuration配置下的类中注入bean
 * @Since JDK1.8
 * @Createtime 2018/9/15 下午 2:28
 * @Author xiechongyang
 */
//@Component
public class SpringContextUtil implements ApplicationContextAware {


    /**
     * spring上下文环境
     */
    private static ApplicationContext applicationContext;

//    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 按bean-id与类型从spring环境中取得bean实例
     *
     * @param id
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(String id, Class<T> type) {
        return applicationContext.getBean(id, type);
    }

    /**
     * 按bean类型从spring环境中取得bean实例列表
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeans(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 按bean类型从spring 容器中获取实例
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

}
