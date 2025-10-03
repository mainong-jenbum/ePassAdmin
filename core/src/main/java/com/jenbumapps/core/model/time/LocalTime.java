package com.jenbumapps.core.model.time;

import org.parceler.Parcel;

import java.util.Calendar;

/**
 * Meant to be used only for Android Development Purpose
 *
 * @author RAM MAHARAJ
 */
@Parcel
public class LocalTime {

    private int hour;
    private int minute;
    private int second;
    private int nano;

    public LocalTime() {
    }

    public LocalTime(int hour, int minute, int second, int nano) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.nano = nano;
    }


    public static LocalTime of(int hour, int minute, int second, int nano) {
        return new LocalTime(hour, minute, second, nano);
    }

    public static LocalTime of(int hour, int minute, int second) {
        return new LocalTime(hour, minute, second, 0);
    }

    public static LocalTime of(int hour, int minute) {
        return new LocalTime(hour, minute, 0, 0);
    }

    public static LocalTime of(Calendar c) {
        return new LocalTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND), 0);
    }

    public static LocalTime now() {
        Calendar c = Calendar.getInstance();
        return new LocalTime(c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND), 0);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getNano() {
        return nano;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }

    public Calendar getTime() {
        Calendar c = Calendar.getInstance();
        c.set(0, 0, 0, hour, minute, second);
        return c;
    }

}
