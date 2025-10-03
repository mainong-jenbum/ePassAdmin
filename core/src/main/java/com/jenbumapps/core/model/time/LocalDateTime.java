package com.jenbumapps.core.model.time;

import org.parceler.Parcel;

import java.util.Calendar;


/**
 * Meant to be used only for Android Development Purpose
 *
 * @author RAM MAHARAJ
 */
@Parcel
public class LocalDateTime {
    private LocalDate date;
    private LocalTime time;

    public LocalDateTime() {
    }

    public LocalDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public static LocalDateTime of(LocalDate date, LocalTime time) {
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(Calendar c) {
        return new LocalDateTime(LocalDate.of(c), LocalTime.of(c));
    }

    public static LocalDateTime now() {
        Calendar c = Calendar.getInstance();
        return new LocalDateTime(LocalDate.of(c), LocalTime.of(c));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}
