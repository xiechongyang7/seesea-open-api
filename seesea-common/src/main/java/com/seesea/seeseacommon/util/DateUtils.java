package com.seesea.seeseacommon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description 日期工具类
 * @Since JDK1.8
 * @Createtime 2018/9/18 15:44
 * @Author xie
 */
public class DateUtils {

    /**
     * 获取二个日期相差几天
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getOverdueDays(Date startTime, Date endTime) {

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(startTime);
        endCal.setTime(endTime);
        int overDays = getDaysBetween(startCal, endCal);
        overDays = Math.abs(overDays);
        return overDays;
    }

    /**
     * 获取两个时间的时间间隔
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int days = endDate.get(Calendar.DAY_OF_YEAR) - beginDate.get(Calendar.DAY_OF_YEAR) + 1;
        int year = endDate.get(Calendar.YEAR);
        if (beginDate.get(Calendar.YEAR) != year) {
            beginDate = (Calendar) beginDate.clone();
            do {
                days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);
                beginDate.add(Calendar.YEAR, 1);
            } while (beginDate.get(Calendar.YEAR) != year);
        }
        return days;
    }

    public static void main(String arg[]) {
//        try {
//            int i  = (int) get("2019-02-05");
//            System.out.println(i+"天过年");
//            System.out.println(i/7+"周");
//            System.out.println(i-(i/7)*2+"天上班");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.exit(0);
//        System.out.println(153.0/7.0);
//        System.out.println(153-(21+12));
//        System.out.println(5*(7000-1700-100-1200-800));
//        System.out.println(((int) (Math.random()*10))%2!=0?"hah":"wuw");
    }

    /**
     * @param a 2018-09-05
     * @return
     */
    public static long get(String a) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        Date d2 = df.parse(a);
        Date d1 = df.parse(getFormatDate(new Date(), "yyyy-MM-dd"));
//        System.out.println((d1.getTime()-d2.getTime())/(60*60*1000*24));
        return -((d1.getTime() - d2.getTime()) / (60 * 60 * 1000 * 24));
    }

    public static String getFormatDate(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat("yyyy-MM-dd");
            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 简化版日期字符串格式码
     */
    public static final String SIMPLE_FULLTIME = "yyyyMMddHHmmss";

    /**
     * 转换简化全时间描述文本为时间对象
     *
     * @param time 描述文本
     * @return 时间对象，如果转换失败返回<code>null</code>
     */
    public static Date parseSimpleFullTime(String time) {
        try {
            return new SimpleDateFormat(SIMPLE_FULLTIME).parse(time);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 转换日志对象为简化全时间描述文本
     *
     * @param time 时间对象
     * @return 描述文本
     */
    public static String toSimpleFullTimeStr(Date time) {
        return new SimpleDateFormat(SIMPLE_FULLTIME).format(time);
    }

}
