package com.seesea.seeseatest.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seesea.seeseacommon.util.HttpUtils;
import com.seesea.seeseacommon.util.JsonUtil;
import com.seesea.seeseatest.bean.Req;

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
         */
        test1();
    }
    private final static String url = "http://127.0.0.1:8002/gateway/api";
    private static void test1() throws JsonProcessingException {


        Req req = new Req();

        req.setSequenceId(System.currentTimeMillis()+"");
        req.setAccountId("111");
        req.setSign("111");
        req.setServiceId("1000001");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("aa","bb");
//        req.setData("{\"aa\":\"bb\"}");

        String reqStr = JsonUtil.objToJson(req);
        System.out.println(reqStr);


        String bytes =  HttpUtils.doPost(url, reqStr);
        System.out.println(bytes);
    }
}