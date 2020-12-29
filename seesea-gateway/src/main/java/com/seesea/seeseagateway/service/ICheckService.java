package com.seesea.seeseagateway.service;

import com.seesea.seeseacommon.base.BaseLogger;
import com.seesea.seeseagateway.entity.Req;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/9/15 上午 1:01
 * @Author xiechongyang
 */
@Service
public interface ICheckService  {

    /**
     * 鉴权
     *
     * @param
     * @return
     */
    boolean check(Req req);
}
