package com.jenbumapps.core.model.time;

import org.parceler.Parcel;

import java.util.Calendar;


/**
 * Meant to be used only for Android Development Purpose
 *
 * @author RAM MAHARAJ
 */
@Parcel
public class LocalDate {

    private int year;
    private int month;
    private int day;

    public LocalDate() {
    }

    public LocalDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static LocalDate of(int year, int month, int dayOfMonth) {
        return new LocalDate(year, month, dayOfMonth);
    }

    public static LocalDate of(Calendar c) {
        return new LocalDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }

    public static LocalDate now() {
        Calendar c = Calendar.getInstance();
        return new LocalDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    public Calendar getDate() {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        return c;
    }

}
