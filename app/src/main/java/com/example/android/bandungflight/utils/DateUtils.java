package com.example.android.bandungflight.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.utils
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class DateUtils {
    protected static Calendar calendar;

    protected static DateFormat monthNameFormat;
    protected static DateFormat monthShortNameFormat;
    protected static DateFormat weekdayNameFormat;
    protected static DateFormat weekdayShortNameFormat;

    public static String getStringDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date);
    }

    public static String getInvertDate(String time) {
        String inputPattern = "dd-MM-yyyy";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getStringDate(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(date);
    }

    public static String getStringDate(String format, long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(DateUtils.longToDate(date));
    }

    public static String getStringDate2(String format, long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(DateUtils.longToDate2(date));
    }

    public static long parseStringDate(String strDate, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(strDate);

            return dateToLong(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String getDateFormatCard(Date date) {
        return DateUtils.weekdayNameFormat().format(date) + ", " + DateUtils.getStringDate("dd", date) + " " + DateUtils.monthNameFormat().format(date);
    }

    public static DateFormat monthNameFormat() {
        return monthNameFormat;
    }

    public static DateFormat monthShortNameFormat() {
        return monthShortNameFormat;
    }

    public static DateFormat weekdayNameFormat() {
        return weekdayNameFormat;
    }

    public static DateFormat weekdayShortNameFormat() {
        return weekdayShortNameFormat;
    }

    public static void setMonthsName(String[] newMonths) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setMonths(newMonths);
        monthNameFormat = new SimpleDateFormat("MMMM", symbols);
    }

    public static void setShortMonthsName(String[] newShortMonths) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setShortMonths(newShortMonths);
        monthShortNameFormat = new SimpleDateFormat("MMM", symbols);
    }

    public static void setWeekdaysName(String[] newWeekdays) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setWeekdays(newWeekdays);
        weekdayNameFormat = new SimpleDateFormat("EEEE", symbols);
    }

    public static void setShortWeekdaysName(String[] newShortWeekdays) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setShortWeekdays(newShortWeekdays);
        weekdayShortNameFormat = new SimpleDateFormat("EEE", symbols);
    }

    public static Date longToDate(long val) {
        return new Date(val * 1000);
    }

    public static Date longToDate2(long val) {
        return new Date(val);
    }

    public static long dateToLong(Date val) {
        return val.getTime() / 1000;
    }

    public static Date today() {
        calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date tomorrow() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date nextYear() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    public static Date nextMonth() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static String[] monthLabels() {
        String[] titles = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

        return titles;
    }

    public static String[] monthLabelsShort() {
        String[] titles = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};

        return titles;
    }

    public static String[] weekDayLabels() {
        String[] titles = {"#", "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        return titles;
    }

    public static String[] weekDayLabelsShort() {
        String[] titles = {"#", "Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};

        return titles;
    }
}

