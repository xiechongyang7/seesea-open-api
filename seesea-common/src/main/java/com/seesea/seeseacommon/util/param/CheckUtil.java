package com.seesea.seeseacommon.util.param;

import com.seesea.seeseacommon.util.RegexUtils;
import com.seesea.seeseacommon.util.param.annotation.Allow;
import com.seesea.seeseacommon.util.param.annotation.NotNull;
import com.seesea.seeseacommon.util.param.annotation.Type;
import com.seesea.seeseacommon.util.param.annotation.TypeNumber;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/2 下午 5:33
 * @Author xiechongyang
 */
public class CheckUtil {

    /**
     * 校验属性的值 配合自定义注解
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, String> checking(Object obj) throws Exception {
        Map<String, String> result = new HashMap<>();
        result.put("paramError", "false");
        Field[] fields = ReflectionUtil.getDeclaredField(obj);
        for (Field field : fields) {
            String filedName = field.getName();
            Object singleParam = ReflectionUtil.getInvoke(obj, filedName, false);
            if (field.isAnnotationPresent(NotNull.class)) {
                if (singleParam == null || "".equals(singleParam)) {
                    result.put("paramError", "true");
                    result.put("errorField", filedName);
                    break;
                }
            } else if (field.isAnnotationPresent(TypeNumber.class)) {
                TypeNumber typeNumber = field.getAnnotation(TypeNumber.class);
                boolean notnull = typeNumber.notNull();
                if (notnull) {
                    if (singleParam == null || "".equals(singleParam)) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "不能为空");
                        break;
                    }
                }
                if (singleParam != null && !"".equals(singleParam)) {
                    int max = typeNumber.maxLength();
                    int min = typeNumber.minLength();
                    if (singleParam.toString().getBytes().length < min) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "长度不能小于" + min);
                        break;
                    } else if (singleParam.toString().getBytes().length > max) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "长度不能大于" + max);
                        break;
                    }
                }
            } else if (field.isAnnotationPresent(Allow.class)) {
                Allow allow = field.getAnnotation(Allow.class);
                boolean notnull = allow.notNull();
                if (notnull) {
                    if (singleParam == null || "".equals(singleParam)) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "不能为空");
                        break;
                    }
                }
                if (singleParam != null && !"".equals(singleParam)) {
                    String[] allows = allow.allows();
                    Arrays.sort(allows);
                    int index = Arrays.binarySearch(allows, singleParam.toString());
                    if (index < 0) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "必须在" + allows + "之中");
                        break;
                    }
                }

            } else if (field.isAnnotationPresent(Type.class)) {
                Type allow = field.getAnnotation(Type.class);
                boolean notnull = allow.notNull();
                if (notnull) {
                    if (singleParam == null || "".equals(singleParam)) {
                        result.put("paramError", "true");
                        result.put("errorField", filedName + "不能为空");
                        break;
                    }
                }
                if (singleParam != null && !"".equals(singleParam)) {
                    String type = allow.type();
                    if ("PHONE".equalsIgnoreCase(type)) {
                        if (!RegexUtils.isPhone(singleParam.toString())) {
                            result.put("paramError", "true");
                            result.put("errorField", filedName + "要填写正确的手机号");
                            break;
                        }
                    } else if ("EMAIL".equalsIgnoreCase(type)) {
                        if (!RegexUtils.isEmail(singleParam.toString())) {
                            result.put("paramError", "true");
                            result.put("errorField", filedName + "要填写正确的邮箱");
                            break;
                        }
                    } else if ("VCODE".equalsIgnoreCase(type)) {
                        if (!RegexUtils.isVCode(singleParam.toString())) {
                            result.put("paramError", "true");
                            result.put("errorField", filedName + "要填写正确的四位验证码");
                            break;
                        }
                    }
                }

            }

        }
        return result;
    }

}
