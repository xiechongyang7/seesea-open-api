package com.seesea.seeseatest.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seesea.seeseacommon.util.*;
import com.seesea.seeseatest.bean.Req;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/11/24 16:41
 */
public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        /**
         * 1 pre 异常
         * 2 error 异常
         * 3 post 异常
         * 4 pre 异常 error 异常
         * 5 error 异常 post 异常
         * 6 post 异常 error 异常
         * { "code": "9003", "msg": "参数错误[workType不能为空]", "reqId": null, "sequenceId": null, "accountId": null, "data": null }
         * { "phone":"15552894757", "workType":"1", "userId":"12121", "smsId":"12121", "sequenceId":"2210210", "accountId":"21212", "reqId":"12121" }
         * { "phone":"15552894757", "workType":"1", "userId":"12121", "smsId":"12121", "sequenceId":"2210210", "accountId":"21212", "reqId":"12121" }
         */
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final static String url = "http://127.0.0.1:8002/gateway/api";
    private static void test1() throws JsonProcessingException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {


        Req req = new Req();

        req.setSequenceId(System.currentTimeMillis()+"");
        req.setAccountId("11111");
        req.setServiceId("1000001");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("aa","bb");

        String dataStr = AESUtil.encrypt("{\"phone\":\"15552894757\",\"workType\":\"1\",\"userId\":\"12121\",\"smsId\":\"12121\"}","111");
        req.setData(dataStr);
        String sortStr = StringUtil.sortValue(req);
        String sign = MD5Util.getMd5(sortStr + "&KEY=" + "111");
        req.setSign(sign);

        String reqStr = JsonUtil.objToJson(req);
        System.out.println(reqStr);


        String bytes =  HttpUtils.doPost(url, reqStr);
        System.out.println(bytes);
    }
}