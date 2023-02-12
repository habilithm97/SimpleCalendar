package com.example.simplecalendar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public final static String TITLE = "yyyy년 MM월";
    public final static String DAY = "d";

    public static String getDate(long date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.KOREA);
            Date d = new Date(date);
            return simpleDateFormat.format(d).toUpperCase();
        } catch (Exception e) {
            return " ";
        }
    }
}
