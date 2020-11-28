package com.seesea.seeseacommon.asserts;

import com.seesea.seeseacommon.base.exception.BizException;
import com.seesea.seeseacommon.common.ResultCode;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/26 下午 11:04
 * @Author xiechongyang
 */
public class Assert {


    public static void isNullStr(String str, ResultCode resultCode) throws BizException {
        if ("".equals(str) || str.length() == 0) {
            throw new BizException(resultCode);
        }
    }

    public static void isNull(Object obj, ResultCode resultCode) throws BizException {
        if (obj == null || "".equals(obj)) {
            throw new BizException(resultCode);
        }
    }

    public static void main(String ay[]) {
        System.out.println("qwe123" == null || "".equals("qwe"));
    }
}
