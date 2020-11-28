package com.seesea.seeseacommon.base.exception;

import com.seesea.seeseacommon.common.ResultCode;

/**
 * @Description 业务异常异常
 * @Since JDK1.8
 * @Createtime 2018/9/16 下午 5:29
 * @Author xiechongyang
 */
public class BizException extends Exception {

    public String errCode;

    public BizException() {
        super();
    }

    public BizException(BizException e) {
        super(e.getMessage());
        this.errCode = e.errCode;
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.msg);
        this.errCode = resultCode.code;
    }
    public BizException(ResultCode resultCode,String format) {
        super(String.format(resultCode.msg,format));
        this.errCode = resultCode.code;
    }
    public BizException(String code,String msg) {
        super(String.format(msg));
        this.errCode = code;
    }
}
