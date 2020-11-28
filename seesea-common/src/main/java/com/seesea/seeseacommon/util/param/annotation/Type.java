package com.seesea.seeseacommon.util.param.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/24 下午 11:27
 * @Author xiechongyang
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {

    boolean notNull();

    /**
     * 包括的类型
     * phone
     * email
     * vCode
     *
     * @return
     */
    String type();
}
