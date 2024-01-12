package com.forex.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parse(String dateString, String formatString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        return formatter.parse(dateString);
    }
}
