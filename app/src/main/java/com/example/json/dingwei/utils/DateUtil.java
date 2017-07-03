package com.example.json.dingwei.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;



public class DateUtil {

    public static String getCurrentRFC1123DateTimeString() {
        Date date = Calendar.getInstance().getTime();
        return getREF1123DateTimeString(date);
    }

    public static String getREF1123DateTimeString(Date date) {
        String datePatternRFC1123 = "E, d MMM y HH:mm:ss 'GMT'";
        return getGMTDateTimeString(date, datePatternRFC1123);
    }

    public static String getCurrentDateTimeString() {
        Date date = Calendar.getInstance().getTime();
        String datePattern = "yyyyMMddHHmmssSSS";
        return getGMTDateTimeString(date, datePattern);
    }

    public static String getCurrentHourTime() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        return sdf.format(date);
    }

    public static String getTimeString(long time) {
        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdr2 = new SimpleDateFormat("MM月dd日");
        String CreatedTime1 = sdr1.format(new Date(time));//获取视频创建时间 格式：yyyy-MM-dd HH:mm:ss
        String CreatedTime2 = sdr2.format(new Date(time));//获取视频创建时间 格式：MM月dd日
        String NowTime = sdr1.format(new Date());//获取当前时间 格式：yyyy-MM-dd HH:mm:ss
        try {
            Date d1 = sdr1.parse(CreatedTime1);
            Date d2 = sdr1.parse(NowTime);
            long diff = d2.getTime() - d1.getTime();//这样得到的时间差值
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            if (days == 0 && hours == 0) {
                return (minutes + "  minute(s) ago");
            } else if (1 <= (24 * days + hours) && (24 * days + hours) <= 24) {
                return ((24 * days + hours) + " hour(s) ago");
            } else if (24 < (24 * days + hours) && days <= 15) {
                return (days + " day(s) ago");
            } else {
                return (CreatedTime2);
            }
        } catch (Exception e) {
        }
        return "The time is wrong";
    }

    private static String getGMTDateTimeString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    private static String getLocalDateTimeString(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

}
