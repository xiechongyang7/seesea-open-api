package com.seesea.seeseacommon.util.param.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/2 下午 6:16
 * @Author xiechongyang
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Allow {

    public boolean notNull();

    public String[] allows();
}
