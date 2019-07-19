package com.ranjay.udacity.models;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timestamps extends StoredObject {
    private String start_time;
    private int hour;
    private int day;
    private int week;
    private int month;
    private int year;
    private String weekday;

    public Timestamps() {
    };

    public Timestamps(Long time) {
        
        Date date = new Date(time);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.day = calendar.get(Calendar.DAY_OF_WEEK);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.week = calendar.get(Calendar.WEEK_OF_MONTH);
        this.weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).toString();
        this.start_time = localDateFormat.format(date);
    }

    public Timestamps(TStamps ts) {
        Date date = new Date(ts.getTs());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(ts.getTs());
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.day = calendar.get(Calendar.DAY_OF_WEEK);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.week = calendar.get(Calendar.WEEK_OF_MONTH);
        this.weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).toString();
        this.start_time = localDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "{" + " start_time='" + getStart_time() + "'" + ", hour='" + getHour() + "'" + ", day='" + getDay() + "'"
                + ", week='" + getWeek() + "'" + ", month='" + getMonth() + "'" + ", year='" + getYear() + "'"
                + ", weekday='" + getWeekday() + "'" + "}";
    }

  
    

}
