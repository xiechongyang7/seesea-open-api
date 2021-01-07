package com.seesea.seeseafinance.service;

import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/29 16:12
 */
public class CalculationService {

    public static void main(String[] args) {
//        calculation(new BigDecimal("3.2367"),new BigDecimal("3.3099"),"2020-12-17","2020-12-21",3);
//        calculation(new BigDecimal("3.2367"),new BigDecimal("3.3099"),"2020-12-17","2020-12-21",3);
//        calculation(new BigDecimal("3.2367"),new BigDecimal("3.3099"),"2020-12-17","2020-12-21",3);
//        calculation(new BigDecimal("16.1987"),new BigDecimal("13.6777"),"2020-11-23","2020-12-23",6);
//        calculation(new BigDecimal("39.8108"),new BigDecimal("37.6714"),"2020-11-24","2020-12-28",1);
//        calculation(new BigDecimal("39.8108"),new BigDecimal("37.6714"),"2020-11-24","2020-12-28",1);
//        calculation(new BigDecimal("46.9909"),new BigDecimal("47.007"),"2020-12-23","2020-12-29",2);
//        calculation(new BigDecimal("16.673"),new BigDecimal("20.184"),"2020-12-29","2020-12-31",3);
        calculation(new BigDecimal("3.3516"),new BigDecimal("3.5648"),"2020-12-29","2021-01-05",32);

//        count(
//                "-29.4\n" +
////
//                "-1512.57\n" +
//                "-213.94\n"
//
//        );
    }

public static void count(String a){

        String[] as = a.split("\n");
        BigDecimal az = new BigDecimal("0");
        for(int i= 0;i< as.length;i++){
            az = az.add(new BigDecimal(as[i]));
        }
        System.out.println(az);
}

//43314.64
//
//        45659.72
//
//
//
//
//
//        2,346.03

    public static void calculation(BigDecimal mairu, BigDecimal maichu, String m,String md,int shu){

        BigDecimal maiz = mairu.multiply(new BigDecimal(shu*100)).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal maic = maichu.multiply(new BigDecimal(shu*100)).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal yingli = maic.subtract(maiz);
        BigDecimal baifen = yingli.divide(maiz,3,BigDecimal.ROUND_HALF_DOWN);
        baifen = baifen.multiply(new BigDecimal(100)).setScale(1,BigDecimal.ROUND_DOWN);
        int day = DateUtil.getOverdueDays(DateUtil.parseSimpleDateStr_(m),DateUtil.parseSimpleDateStr_(md));

        BigDecimal riying = yingli.divide(new BigDecimal(day),3,BigDecimal.ROUND_HALF_DOWN);
//  +"         "+
        System.out.println(mairu+"     "+ maiz+"         "+maichu+"         "+maic+"         "+shu+"     "+getStr(m, md)+"        "+day+"     "+ baifen+"%     "+ riying+"     "+ yingli);


    }

    public static String getStr(String m,String md){

       return
        (m.substring(5)).replace("-",".")
                +"-"+
        (md.substring(5)).replace("-",".")
                ;

    }
    
    
}
