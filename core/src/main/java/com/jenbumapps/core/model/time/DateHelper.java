package com.jenbumapps.core.model.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public static String formatDate(Date chosenDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM, yyyy", Locale.ENGLISH); // Set your date format
        return sdf.format(chosenDate);
    }

    public static String formatDate(LocalDateTime date) {
        return formatDate(date.getDate().getDate().getTime());
    }

    public static String formatTime(LocalDateTime time){
        int sec = time.getTime().getSecond();
        int min = time.getTime().getMinute();
        int hour = time.getTime().getHour();
        boolean pm = false;

        if(hour>12){
            pm = true;
            hour = hour - 12;
        }

        return pm ? ""+hour +":"+min+":"+sec+" PM" : ""+hour +":"+min+":"+sec+" AM";
    }

    public static String formatTime(LocalTime time){
        int sec = time.getSecond();
        int min = time.getMinute();
        int hour = time.getHour();
        boolean pm = false;

        if(hour>12){
            pm = true;
            hour = hour - 12;
        }

        return pm ? ""+hour +":"+min+":"+sec+" PM" : ""+hour +":"+min+":"+sec+" AM";
    }


}
