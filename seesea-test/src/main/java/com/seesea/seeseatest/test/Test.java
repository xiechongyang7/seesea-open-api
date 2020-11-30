package com.seesea.seeseatest.test;

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

    public static void main(String[] args) {
        test1();
    }
    private final static String url = "http://127.0.0.1:8002/gateway/api";
    private static void test1(){

        Req req = new Req();

        req.setSequenceId(System.currentTimeMillis()+"");
        req.setAccountId("111");
        req.setSign("111");
        req.setServiceId("1000001");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("aa","bb");
        req.setData(map);

        String reqStr = JsonUtil.objToJson(req);
        System.out.println(reqStr);


        byte[] bytes =  HttpUtils.doPost(url, reqStr);
        System.out.println(new String(bytes));
    }
}