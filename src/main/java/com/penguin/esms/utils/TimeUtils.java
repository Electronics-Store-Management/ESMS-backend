package com.penguin.esms.utils;

import java.util.Date;

public class TimeUtils {
    public static long getDay(Date date) {
        return date.getTime() / 86400000;
    }

    public static long getDay(Long date) {
        return date / 86400000;
    }
}
