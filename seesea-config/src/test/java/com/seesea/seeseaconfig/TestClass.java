package com.seesea.seeseaconfig;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/11/17 15:58
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfigurationpublic
class TestClass {
    @Autowired
    StringEncryptor encryptor;
    @Test
    public void getPass() {
        String url = encryptor.encrypt("amqp://rmfntena:5IKC8mBMYuLR5Nnk5VTjakaXkuA4O6_t@mustang.rmq.cloudamqp.com/rmfntena");
        String name = encryptor.encrypt("rmfntena");
        String password = encryptor.encrypt("5IKC8mBMYuLR5Nnk5VTjakaXkuA4O6_t");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
        System.out.println(password+"----------------");
//        Assert.assertTrue(name.length() > 0);
//        Assert.assertTrue(password.length() > 0);
    }
}