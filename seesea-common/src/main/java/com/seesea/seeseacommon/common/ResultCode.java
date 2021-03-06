package com.seesea.seeseacommon.common;

public enum ResultCode {


    SUCCESS("200", "success"),
    ERR("9999", "服务器异常[%s]"),

    ER_9000 ("ER_9000","参数有误空指针异常"),
    ER_9001 ("9001","未知异常"),
    ER_9002 ("9002","参数有误空数据库操作异常"),
    ER_9003 ("9003","参数错误[%s]"),
    /**
     * =====================================================网关错误码 10 start=====================================================
     */

    ER_1000("1000", "流水号重复"),
    ER_1001("1001", "网关验签失败"),
    ER_1002("1002", "网关鉴权失败"),
    ER_1003("1003", "网关检查缺少必要参数"),
    ER_1004("1004", "服务返回参数处理错误"),
    ER_1005("1005", "服务ID错误"),
    ER_1006("1006", "请求流水号或服务ID不能为空"),
    ER_1007("1007", "解密参数异常"),
    ER_1008("1008", "网关通讯异常"),
    ER_1009("1009", "未通过网关检查"),
    ER_1010("1010", "网关未知异常"),

    /**
     * =====================================================网关错误码 10 end=====================================================
     */
    /**
     * =====================================================短信服务错误码 12 start=====================================================
     */
    ER_1200("1200","该短信不支持此方法"),
    ER_1201("1201","短信已经发送请稍后再试"),
    ER_1202("1202","无可用的短信通道"),
    ER_1203("1203","发送短信参数转化异常"),
    ER_1204("1204","短信通道没有返回信息"),
    ER_1205("1205","短信通道没有返回有效信息"),
    ER_1206("1206","一天只能发送[%s]次"),
    ER_1207("1207","您还未发送验证码短信"),
    ER_1208("1208","验证码错误(),验短信次数超过[%s],此短信失效)"),
    ER_1209("1209","验证码短信过期"),
    ER_1210("1210","验证码错误"),
    /** =====================================================短信服务错误码 12 end===================================================== */

    ;


    public String code;
    public String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
