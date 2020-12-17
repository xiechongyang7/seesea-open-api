package com.seesea.seeseafegin.api;

import com.seesea.seeseafegin.entity.BillingVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/17 15:34
 */
@FeignClient("seesea-billing")
public interface BillingApi {

    @PostMapping(value = "/billing/v1/billing")
    Boolean billing(@RequestBody BillingVo ao);

}
