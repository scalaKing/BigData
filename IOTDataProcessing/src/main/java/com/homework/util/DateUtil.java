package com.homework.util;

/**
 * @author: jinyibin
 * @date: 2021/5/31
 */


import com.homework.constant.DateConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static Calendar calendar = null;
    private static SimpleDateFormat sdf = null;

    /**
     * transform the date format into a String
     * @param format
     * @param date
     * @return
     */
    public static String formatDate(String format, Date date) {
        sdf = new SimpleDateFormat(format);
        String res = null;
        synchronized (DateUtil.class) {
            res = sdf.format(date);
            return res;
        }
    }

    /**
     * Parse the String object into a date object
     * @param format
     * @param dateStr
     * @return
     */
    public static Date date2Str(String format, String dateStr) {
        Date date = null;
        try {
            sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * get current timestamp
     * @return
     */
    public static Long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * get current time with format
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        if (null == format || format.isEmpty()) {
            return getCurrentTime();
        }
        String res = null;
        synchronized (DateUtil.class) {
            res = formatDate(format, new Date());
            return res;
        }
    }

    /**
     * get current time with default format
     * @return
     */
    public static String getCurrentTime() {
        String res = null;
        synchronized (DateUtil.class) {
            res = formatDate(DateConstant.YYYY_MM_DD_MM_SS_SSS_FORMAT, new Date());
            return res;
        }
    }

    /**
     * get current date with format
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        if (null==format || format.isEmpty()) {
            return getCurrentDate();
        }
        String res = null;
        synchronized (DateUtil.class) {
            res = formatDate(format, new Date());
            return res;
        }
    }

    /**
     * Get current Date
     * @return
     */
    public static String getCurrentDate() {
        String res = null;
        synchronized (DateUtil.class) {
            res = formatDate(DateConstant.YYYY_MM_DD_FORMAT, new Date());
            return res;
        }
    }

    public static String getCurrentMonth() {
        String res = null;
        synchronized (DateUtil.class) {
            res = formatDate(DateConstant.YYYY_MM_FORMAT, new Date());
            return res;
        }
    }

    /**
     * convert timestamp format into date
     * @param timeStamp
     * @param pattern
     * @return
     */
    public static String timeStamp2Date(Long timeStamp, String pattern) {
        Date date = new Date(timeStamp);
        sdf = new SimpleDateFormat(pattern);
        synchronized (DateUtil.class) {
            return sdf.format(date);
        }
    }

    /**
     * Convert date to timestamp format
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Long date2TimeStamp(String dateStr, String pattern) {
        String res;
        Date date;
        Long ts = null;
        try {
            sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(dateStr);
            ts = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ts;

    }

    /**
     * Get other days via current date
     * @param format
     * @param days
     * @return
     */
    public static String getOtherDay(String format, int days) {
        Calendar cal;
        try {
            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);
            String yesterday = new SimpleDateFormat(format).format(cal.getTime());
            return yesterday;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get Yesterday with specified format
     * @return
     */
    public static String getYesterDay(String format) {
        return getOtherDay(format, -1);
    }

    /**
     * Get Yesterday
     * @return
     */
    public static String getYesterDay() {
        return getYesterDay(DateConstant.YYYY_MM_DD_FORMAT);
    }

    public static void main(String[] args) {
        System.out.println(date2TimeStamp("2022-05-20 11:28:58", DateConstant.YYYY_MM_DD_MM_SS_FORMAT));
        System.out.println(getYesterDay());
        System.out.println(getCurrentMonth());
    }
}
