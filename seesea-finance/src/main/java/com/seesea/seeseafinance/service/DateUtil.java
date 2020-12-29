package com.seesea.seeseafinance.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/** 完整时间字符串格式码 */
	public static final String FULL_TIME = "yyyy-MM-dd HH:mm:ss";
	/** 标准日期字符串格式码 */
	public static final String DATE = "yyyy-MM-dd";
	/** 标准时间字符串格式码 */
	public static final String TIME = "HH:mm:ss";
	/** 简化版全时间字符串格式码 */
	public static final String SIMPLE_FULLTIME = "yyyyMMddHHmmss";
	/** 简化版日期字符串格式码 */
	public static final String SIMPLE_DATE = "yyyyMMdd";
	/** 简化版时间字符串格式码 */
	public static final String SIMPLE_TIME = "HHmmss";

	private static final SimpleDateFormat SDF_TIME6 = new SimpleDateFormat("HHmmss");

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

	/**
	 * 取得相对当前系统时间指定偏移天数的日期对象
	 * 
	 * @param offsetDays 偏移天数
	 * @return 日期对象
	 */
	public static Date getDate(int offsetDays) {
		return new Date(System.currentTimeMillis() + offsetDays * 3600000L * 24L);
	}

	/**
	 * 转换日志对象为标准全时间描述文本
	 * 
	 * @param time 时间对象
	 * @return 描述文本
	 */
	public static String toFullTimeStr(Date time) {
		return new SimpleDateFormat(FULL_TIME).format(time);
	}

	/**
	 * String类型yyyyMMddHHmmss时间转换为String类型yyyy-MM-dd日期
	 * 
	 * @param time "yyyyMMddHHmmss"
	 * @return
	 */
	public static String fullTimeStrToDateStr(String time) {
		return new SimpleDateFormat(DATE).format(parseSimpleFullTime(time));
	}

	/**
	 * 转换日期对象为标准日期描述文本
	 * 
	 * @param time 时间对象
	 * @return 描述文本
	 */
	public static String toDateStr(Date time) {
		return new SimpleDateFormat(DATE).format(time);
	}

	/**
	 * 转换日期对象为标准时间描述文本
	 * 
	 * @param time 时间对象
	 * @return 描述文本
	 */
	public static String toTimeStr(Date time) {
		return new SimpleDateFormat(TIME).format(time);
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
	 * 日期计算 指定日期YYYYMMDDHHMMSS + 分 eg: getToDt("20170101155030", "15") = Sun Jan 01 16:05:30 CST 2017
	 * 
	 * @param time
	 * @param minutes
	 * @return
	 */
	public static Date getToDt(String minutes) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + Integer.parseInt(minutes));
		return calendar.getTime();
	}

	/**
	 * yyyy-MM-dd to Date
	 * 
	 * @param time
	 * @return
	 */
	public static Date parseSimpleDateStr_(String time) {
		try {
			return new SimpleDateFormat(DATE).parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
    /**
     * yyyy-MM-dd HH:mm:ss to Date
     *
     * @param time
     * @return
     */
    public static Date parseSimpleDateStrFull(String time) {
        try {
            return new SimpleDateFormat(FULL_TIME).parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
	/**
	 * yyyymmdd to Date
	 * 
	 * @param time
	 * @return
	 */
	public static Date parseSimpleDateStrTW_(String time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 转换日期对象为简化日期描述文本
	 * 
	 * @param time 时间对象
	 * @return 描述文本
	 */
	public static String toSimpleDateStr(Date time) {
		return new SimpleDateFormat(SIMPLE_DATE).format(time);
	}

	/**
	 * 转换简化日期描述文本为时间对象
	 * 
	 * @param time 描述文本
	 * @return 时间对象，如果转换失败返回<code>null</code>
	 */
	public static Date parseSimpleDate(String time) {
		try {
			return new SimpleDateFormat(SIMPLE_DATE).parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 比较简化日期格式数据
	 * 
	 * @param source 原日期
	 * @param target 目标日期
	 * @return 如果原日期小于目标日期，返回负数；如果原日期等于目标日期，返回零；如果原日期大于目标日期，返回正数
	 */
	public static int compareSimpleDate(String source, String target) {
		Date s = parseSimpleDate(source);
		Date t = parseSimpleDate(target);
		return s.compareTo(t);
	}

	/**
	 * 转换日期对象为简化时间描述文本
	 * 
	 * @param time 时间对象
	 * @return 描述文本
	 */
	public static String toSimpleTimeStr(Date time) {
		return new SimpleDateFormat(SIMPLE_TIME).format(time);
	}

	/**
	 * 取得指定日期所在星期的星期几对应日期对象
	 * 
	 * @param time 指定日期
	 * @param day 星期几，如<code>Calendar.MONDAY</code>
	 * @return 指定日期所在星期的参数星期几对应日期对象
	 */
	public static Date getDay(Date time, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	/**
	 * 取得当前系统日期所在星期的星期几对应日期对象
	 * 
	 * @param day 星期几，如<code>Calendar.MONDAY</code>
	 * @return 对应日期对象
	 */
	public static Date getDay(int day) {
		return getDay(new Date(), day);
	}

	/**
	 * 取得指定日期后的最近一个周几对应日期对象
	 * 
	 * @param time 指定日期
	 * @param day 周几
	 * @return 对应日期对象
	 */
	public static Date nextDay(Date time, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_WEEK, day);
		if (cal.getTimeInMillis() < time.getTime()) {
			cal.add(Calendar.DATE, 7);
		}
		return cal.getTime();
	}

	/**
	 * 取得当前日期后的最近一个周几对应日期对象
	 * 
	 * @param day 周几
	 * @return 对应日期对象
	 */
	public static Date nextDay(int day) {
		return nextDay(new Date(), day);
	}

	/**
	 * 加减天数
	 * 
	 * @param num
	 * @param Date
	 * @return
	 */
	public static Date addDay(int num, Date Date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date);
		calendar.add(Calendar.DATE, num);// 把日期往后增加 num 天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 比较日期大小
	 * 
	 * @param src
	 * @param src
	 * @return int; 1:DATE1>DATE2;
	 */
	public static int compare_date(Date src, Date src1) {

		String date1 = convertDate2String(FULL_TIME, src);
		String date2 = convertDate2String(FULL_TIME, src1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 转换日期得到指定格式的日期字符串
	 * 
	 * @param formatString 需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
	 * @param targetDate 目标日期
	 * @return
	 */
	public static String convertDate2String(String formatString, Date targetDate) {
		SimpleDateFormat format = null;
		String result = null;
		if (targetDate != null) {
			format = new SimpleDateFormat(formatString);
			result = format.format(targetDate);
		} else {
			return null;
		}
		return result;
	}

	/**
	 * 加减分
	 * 
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addMin(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num);// 把日期往后增加 num 天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 加减年
	 * 
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addYear(int num, Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		return calendar.getTime();
	}

	/**
	 * 加减月
	 * 
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addMon(int num, Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		return calendar.getTime();
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 *
	 * @return nowTimeStamp
	 */
	public static String getNowTimeStamp() {
		long time = System.currentTimeMillis();
		String nowTimeStamp = String.valueOf(time / 1000);
		return nowTimeStamp;
	}
	/**
	 * 获取当前6位时间
	 * 
	 * @return
	 *
	 * @authz Reaper.M
	 * @createtime 2017年8月22日 下午12:58:25
	 */
	public static String getTime() {

		return SDF_TIME6.format(new Date());
	}
	/**
	 * 判断日期的格式是否为年月日型
	 * @param str
	 * @return
	 */
	public static boolean isDateFormat(String str){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try{
			format.parse(str);
		}catch(Exception e){
			return false;
		}
			return true;
		}
	
	/**
	 * 特殊时间转换
	 * @param obj
	 * @return
	 */
	public static String timeStampToDate(Object obj){
		
//      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//      return sdf.parse(String.valueOf(obj), new ParsePosition(0));
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sdf.format(new Date(Long.parseLong(String.valueOf(obj))));// 时间戳转换成时间
	}


	/**
	 * @Author lfFan
	 * @Description //获取某天的开始时间  eg:2019-10-10  return 2019-10-10 00:00:00
	 * @Date 10:29 2019/11/2
	 * @Param [date]
	 * @return java.util.Date
	 **/
	public static Date getDayStartTime(Date date) {
		Calendar dateStart = Calendar.getInstance();
		dateStart.setTime(date);
		dateStart.set(Calendar.HOUR_OF_DAY, 0);
		dateStart.set(Calendar.MINUTE, 0);
		dateStart.set(Calendar.SECOND, 0);
		return dateStart.getTime();
	}

	/**
	 * @Author lfFan
	 * @Description //获取某天的结束时间  eg:2019-10-10  return 2019-10-10 23:59:59
	 * @Date 10:29 2019/11/2
	 * @Param [date]
	 * @return java.util.Date
	 **/
	public static Date getDayEndTime(Date date) {
		Calendar dateEnd = Calendar.getInstance();
		dateEnd.setTime(date);
		dateEnd.set(Calendar.HOUR_OF_DAY, 23);
		dateEnd.set(Calendar.MINUTE, 59);
		dateEnd.set(Calendar.SECOND, 59);
		return dateEnd.getTime();
	}
	
}
