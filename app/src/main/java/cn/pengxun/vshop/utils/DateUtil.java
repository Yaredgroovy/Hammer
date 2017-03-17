package cn.pengxun.vshop.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liu-feng
 * @date 2017/2/22 0022.
 * Email:w710989327@foxmail.com
 */
public class DateUtil {

    public static final String VZAN_DATE_FORMAT = "yyyy-MM-dd";

    // 把日期转为字符串
    public static String convertDate2String(Date date) {
        DateFormat df = new SimpleDateFormat(VZAN_DATE_FORMAT);
        return df.format(date);
    }

    // 把字符串转为日期
    public static Date convertString2Date(String dateStr) {
        DateFormat df = new SimpleDateFormat(VZAN_DATE_FORMAT);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    // 把字符串转为日期
    public static String convertString2String(String dateStr) {
        DateFormat df = new SimpleDateFormat(VZAN_DATE_FORMAT);
        try {
            return df.format(df.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }
}
