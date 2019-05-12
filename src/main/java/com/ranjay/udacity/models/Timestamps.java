package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class Timestamps implements StoredObject {
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
        Timestamp ts = new Timestamp(time);
        Date date = new Date(ts.getTime());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
        this.hour = date.getHours();
        this.day = date.getDay();
        this.month = date.getMonth();
        this.year = date.getYear();
        this.week = calendar.get(Calendar.WEEK_OF_MONTH);
        this.weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).toString();
        this.start_time = localDateFormat.format(date);
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        String insertTableSQL = "INSERT INTO logtimes" + "(start_time, hour, day, week, month, year, weekday) VALUES"
                + "(?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insertTableSQL);
            statement.setString(1, this.getStart_time());
            statement.setInt(2, this.getHour());
            statement.setInt(3, this.getDay());
            statement.setInt(4, this.getWeek());
            statement.setInt(5, this.getMonth());
            statement.setInt(6, this.getYear());
            statement.setString(7, this.getWeekday());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return statement;

    }


    @Override
    public String toString() {
        return "{" +
            " start_time='" + getStart_time() + "'" +
            ", hour='" + getHour() + "'" +
            ", day='" + getDay() + "'" +
            ", week='" + getWeek() + "'" +
            ", month='" + getMonth() + "'" +
            ", year='" + getYear() + "'" +
            ", weekday='" + getWeekday() + "'" +
            "}";
    }


}