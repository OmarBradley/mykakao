package com.example.mykakao.util;

import android.util.Log;

import org.joda.time.DateTime;

/**
 * Created by 재화 on 2016-04-24.
 */
public class DateFormatter {

    private static final String AM = "오전";
    private static final String PM = "오후";
    private static final String MERIDIEM_AND_HOUR_DIVIDER = " ";
    private static final String ZERO = "0";
    private static final String HOUR_AND_MINUTE_DIVIDER = ":";

    public static String makeChatTimeString() {
        return getMeridiem() + MERIDIEM_AND_HOUR_DIVIDER + getHour() + HOUR_AND_MINUTE_DIVIDER + getMinute();
    }

    private static String getMeridiem() {
        int hour = DateTime.now().getHourOfDay();
        if (hour >= 1 && hour <= 11) {
            return AM;
        } else {
            return PM;
        }
    }

    private static String getHour() {
        int hour = DateTime.now().getHourOfDay();
        if (hour >= 1 && hour <= 11) {
            return Integer.toString(hour);
        } else {
            return Integer.toString(hour - 12);
        }
    }

    private static String getMinute() {
        int minute = DateTime.now().getMinuteOfHour();
        if (minute < 10) {
            return ZERO + Integer.toString(minute);
        } else {
            return Integer.toString(minute);
        }
    }

}
